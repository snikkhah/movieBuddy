package movieBuddy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Mahout {

	private HashMap<String, String> userCities;
	private DataModel model;
	private UserSimilarity similarity;
	UserNeighborhood neighborhood;
	
	public Mahout(){
		try{
			System.out.println("Howdy from Mahout");
			Extractor ext = new Extractor();
			userCities = ext.usersCities(ext.zipcodeExtractor());
			model = new FileDataModel(new File("src/main/resources/ratings.csv"));
			similarity = new PearsonCorrelationSimilarity(model);
	//		UserSimilarity similarity2 = new UncenteredCosineSimilarity(model);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public long[] getAllBuddies(int targetId, double threshold){
		try {
			System.out.print(model);
			neighborhood = new ThresholdUserNeighborhood(threshold, similarity, model);
			return neighborhood.getUserNeighborhood(targetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Long> getLocalBuddies(int targetId, double threshold){
		long[] res = getAllBuddies(targetId, threshold);
		String targetCity = userCities.get(String.valueOf(targetId));
		String recommendedCity;
		ArrayList<Long> closeBuddies = new ArrayList<Long>();
		for (int i=0; i<res.length; i++){
			recommendedCity = userCities.get(String.valueOf(res[i]));
			if (targetCity.equals(recommendedCity))
				closeBuddies.add(res[i]);
		}
		return closeBuddies;
	}
	public HashMap<String, String> getMovieRecommendations(int targetId, double threshold, int No){
		try{
			neighborhood = new ThresholdUserNeighborhood(threshold, similarity, model);
			HashMap<String, String> movies = new HashMap<String, String>();
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(targetId, No);
			for (RecommendedItem recommendation : recommendations) {
				movies.put(String.valueOf(recommendation.getItemID()), String.valueOf(recommendation.getValue()));
//				System.out.println(recommendation);
			}
			return movies;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public void cluster(int targetId) {
		// TODO Auto-generated method stub
		try {
			
			long[] res = neighborhood.getUserNeighborhood(targetId);
			String targetCity = userCities.get(String.valueOf(targetId));
			System.out.println(res.length);
			String recommendedCity;
			for (int i=0; i<res.length; i++){
//				System.out.print(res[i]+"\t");
//				if (i%8==7)
//					System.out.println();
				recommendedCity = userCities.get(String.valueOf(res[i]));
				if (targetCity.equals(recommendedCity))
					System.out.println("We got a match: "+res[i]);
			}
			System.out.println();
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(1, 3);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
