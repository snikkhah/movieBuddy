package movieBuddy;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieService extends Service<ServiceConfiguration>{
	private final Logger log = LoggerFactory.getLogger(getClass());
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new MovieService().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
	bootstrap.setName("Backpack-service");
    }
	@Override
	public void run(ServiceConfiguration configuration, Environment environment)
			throws Exception {
		// TODO Auto-generated method stub
			
//		All the codes that are to handle Sessions
		HashSessionManager manager = new HashSessionManager();
		SessionHandler sessions = new SessionHandler(manager);
		environment.setSessionHandler(sessions);
		environment.addResource(new Resources());
	}
}
