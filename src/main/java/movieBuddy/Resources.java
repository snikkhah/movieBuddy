package movieBuddy;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.collections.map.HashedMap;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import com.yammer.metrics.annotation.Timed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resources {
	private Configuration cfg;
	private Template template;
	private Mahout mahout;
	private Connection c;
	private Statement stmt;
	private HashMap<Integer, String> occupations;
	public Resources() {
		cfg = createFreemarkerConfiguration();
		mahout = new Mahout();
		stmt = null;
		try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/moviebuddy",
	            "postgres", "postgres");
	         stmt = c.createStatement();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	      }
		setOccupations();
	}
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/landing")
	@Timed(name = "home")
	public Response landing() {	
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("landing.ftl");
			SimpleHash root = new SimpleHash();
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 	return Response.status(200).entity(output.toString()).build();
	}
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/home")
	@Timed(name = "home")
	public Response home() {	
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("home.ftl");
			SimpleHash root = new SimpleHash();
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 	return Response.status(200).entity(output.toString()).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/charts")
	@Timed(name = "home")
	public Response charts() {	
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("charts.ftl");
			SimpleHash root = new SimpleHash();
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 	return Response.status(200).entity(output.toString()).build();
	}
	
	@POST
    @Path("/home/allbuddies")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "get-all-buddies")
    public Response getBuddies(HashedMap input) throws TemplateModelException {
    	
		String userId=  (String)input.get("userId");
		String similarity = (String)input.get("similarity");
		if (userId.equals("") )
			return Response.status(400).entity("User ID is missing.").build();
		if (similarity.equals(""))
			return Response.status(400).entity("Similarity coefficient is missing.").build(); 
		double threshhold = Double.parseDouble("0."+similarity);
		long[] buddies=mahout.getAllBuddies(Integer.parseInt(userId),threshhold);
		List<User> users = new ArrayList<User>();
		try {
			
			
			String qParams="";
			for (int i=0; i<buddies.length; i++){
				qParams = qParams+"'"+buddies[i]+"',";
//				users.add(new User((int)buddies[i],'?',0,0,0));
//				System.out.print(buddies[i]+"\t");
//				if (i%8==7)
//					System.out.println();
			}
			if (qParams.length()>1)
				qParams = qParams.substring(0,qParams.length()-1);
//			System.out.println(qParams);
			ResultSet rs = stmt.executeQuery( "select * from users where \"UserID\" in ("+qParams+");" );
			while ( rs.next() ) {
				users.add(new User(rs.getInt(1),rs.getString(2).charAt(0),rs.getInt(3),occupations.get(rs.getInt(4)),rs.getString(5)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("buddies.ftl");
			SimpleHash root = new SimpleHash();
			root.put("users", users);
			root.put("count", users.size());
			root.put("userId", userId);
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		System.out.println(output.toString());
    	return Response.status(200).entity(output.toString()).build();
    }
	
	@POST
    @Path("/home/localbuddies")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "get-local-buddies")
    public Response getLocalBuddies(HashedMap input) throws TemplateModelException {
    	
		String userId= (String)input.get("userId");
		String similarity = (String)input.get("similarity");
		if (userId.equals("") )
			return Response.status(400).entity("User ID is missing.").build();
		if (similarity.equals(""))
			return Response.status(400).entity("Similarity coefficient is missing.").build();
		double threshhold = Double.parseDouble("0."+similarity);
		ArrayList<Long> buddies=mahout.getLocalBuddies(Integer.parseInt(userId),threshhold);
		String qParams="";
		List<User> users = new ArrayList<User>();
		for (int i=0; i<buddies.size(); i++){
			qParams = qParams+"'"+buddies.get(i)+"',";
//			users.add(new User(buddies.get(i).intValue(),'?',0,0,"0"));
//			System.out.print(buddies[i]+"\t");
//			if (i%8==7)
//				System.out.println();
		}
		try {
			if (qParams.length()>1)
				qParams = qParams.substring(0,qParams.length()-1);
			else 
				return Response.status(200).entity("No Hit!").build();
			ResultSet rs = stmt.executeQuery( "select * from users where \"UserID\" in ("+qParams+");" );
			while ( rs.next() ) {
				users.add(new User(rs.getInt(1),rs.getString(2).charAt(0),rs.getInt(3),occupations.get(rs.getInt(4)),rs.getString(5)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("buddies.ftl");
			SimpleHash root = new SimpleHash();
			root.put("users", users);
			root.put("count", users.size());
			root.put("userId", userId);
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		System.out.println(output.toString());
    	return Response.status(200).entity(output.toString()).build();
    }
	
	@POST
    @Path("/home/movies")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "get-movies")
    public Response getMovies(HashedMap input) throws TemplateModelException {
    	
		String userId=  (String)input.get("userId");
		String similarity = (String)input.get("similarity");
		String movieNo = (String)input.get("movieNo");
		if (userId.equals("") )
			return Response.status(400).entity("User ID is missing.").build();
		if (similarity.equals(""))
			return Response.status(400).entity("Similarity coefficient is missing.").build();
		if (movieNo.equals(""))	
			return Response.status(400).entity("Movie Number is missing.").build();
		double threshhold = Double.parseDouble("0."+similarity);
		HashMap<String, String> rawMovies = mahout.getMovieRecommendations(Integer.parseInt(userId),threshhold,Integer.parseInt(movieNo));
//		System.out.println(movies);
		String qParams="";
		List<Movie> movies = new ArrayList<Movie>();
		try{
			for (Map.Entry<String, String> entry : rawMovies.entrySet()) {
			    String key = entry.getKey();
			    qParams = qParams+"'"+key+"',";
			}
			if (qParams.length()>1)
				qParams = qParams.substring(0,qParams.length()-1);
			System.out.println(qParams);
			ResultSet rs = stmt.executeQuery( "select * from movies where \"MovieID\" in ("+qParams+");");
			
			while ( rs.next() ) {
				movies.add(new Movie(rs.getInt(1), rs.getString(2), rs.getString(3),rawMovies.get(String.valueOf(rs.getInt(1)))));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Writer output = new StringWriter();
		try {
			template = cfg.getTemplate("movies.ftl");
			SimpleHash root = new SimpleHash();
			root.put("recMovies", movies);
			root.put("userId", userId);
			template.process(root, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
//		System.out.println(output.toString());
    	return Response.status(200).entity(output.toString()).build();
    }
	private Configuration createFreemarkerConfiguration() {
		Configuration retVal = new Configuration();
		retVal.setClassForTemplateLoading(Resources.class, "/freemarker");
		return retVal;
}
	private void setOccupations(){
		occupations = new HashMap<Integer, String>();
		try {
			ResultSet rs = stmt.executeQuery( "select * from occupations");
			while ( rs.next() ) {
				occupations.put(rs.getInt(1),rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
