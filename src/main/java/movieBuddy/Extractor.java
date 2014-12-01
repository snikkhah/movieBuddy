package movieBuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Extractor {

	public Extractor(){
	}
	public void decompose(){
		HashMap<String, ArrayList<String>> movies = genreExtractor();
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(new File("src/main/resources/ratings.csv")));
			bw = new BufferedWriter(new FileWriter(new File("src/main/resources/ratingsDecomposed.csv")));
			String line = "";
			String[] splits;
			ArrayList<String> genreList = null;
			while((line = br.readLine())!=null){
				try {
					splits = line.split(",");
					genreList = movies.get(splits[1]);
					for (String genre : genreList){
						bw.write(splits[0]+","+ genre + "," + splits[2]+"\n");
						bw.flush();
					}
				}
				catch (NullPointerException e){
					System.out.println(line);
					e.printStackTrace();
				}
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public HashMap<String, ArrayList<String>> genreExtractor(){
		BufferedReader br = null;
		HashMap<String, ArrayList<String>> movies = new HashMap<String, ArrayList<String>>();
		try {
			br = new BufferedReader(new FileReader(new File("src/main/resources/movies.csv")));
			String line = "";
			String[] splits;
			String[] genres;
			ArrayList<String> genreList;
			while((line = br.readLine())!=null){
				genreList = new ArrayList<String>();
				splits = line.split(",");
				genres = splits[2].split("\\|");
				
				for (int i=0;i<genres.length;i++)
					genreList.add(genres[i]);
				movies.put(splits[0], genreList);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	public HashMap<String, String> zipcodeExtractor(){
		BufferedReader br = null;
		HashMap<String, String> zipcodes = new HashMap<String, String>();
		try {
			br = new BufferedReader(new FileReader(new File("src/main/resources/zipcode.csv")));
			String line = "";
			String[] splits;
			br.readLine();
			while((line = br.readLine())!=null){
				splits = line.split(",");
				zipcodes.put(splits[0].substring(1, splits[0].length()-1), splits[1].substring(1, splits[1].length()-1));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipcodes;
	}
	
	public void ratingPerGenre(){
		BufferedReader br = null;
		HashMap<String, Integer> genres = new HashMap<String, Integer>();
		try {
			br = new BufferedReader(new FileReader(new File("src/main/resources/ratingsDecomposed.csv")));
			String line = "";
			String[] splits;
			while((line = br.readLine())!=null){
				splits = line.split(",");
				if (genres.containsKey(splits[1]))
					genres.put(splits[1], genres.get(splits[1])+1);
				else
					genres.put(splits[1], 1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(genres);
	}
	
	public HashMap<String, String> usersCities(HashMap<String, String> zipcodes){
		BufferedReader br = null;
		HashMap<String, String> userCity = new HashMap<String, String>();
		try {
			br = new BufferedReader(new FileReader(new File("src/main/resources/users.csv")));
			String line = "";
			String[] splits;
//			br.readLine();
//			1,F,1,10,48067
			String city; 
			while((line = br.readLine())!=null){
				splits = line.split(",");
				city = splits[4];
				if (city.contains("-"))
					city = city.substring(0, city.indexOf("-"));
				city = zipcodes.get(city);
				if (city==null)
					city = zipcodes.get("94080");
//					System.out.println(splits[4]);
				userCity.put(splits[0], city);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userCity;
	}
	
	public void cityPopulation(){
		HashMap<String, ArrayList<String>> cityPop = new HashMap<String, ArrayList<String>>();
		HashMap<String, String> userCity = usersCities(zipcodeExtractor());
		for (Entry<String, String> entry : userCity.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    if (cityPop.containsKey(value)){
		    	ArrayList<String> tmp = cityPop.get(value);
		    	tmp.add(key);
		    	cityPop.put(value, tmp);
		    }
		    else {
		    	ArrayList<String> tmp = new ArrayList<String>();
		    	tmp.add(key);
		    	cityPop.put(value, tmp);
		    }
//			for (Entry<String, ArrayList<String>> entry : cityPop.entrySet()) {
//			System.out.println(entry.getKey()+"\t"+((ArrayList<String>)entry.getValue()).size());
//			}
		}
	}
}
