import java.util.*;

public class FourthRatings{
    public double getAverageByID(String movieID, int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        int count = 0;
        double sum = 0;
        double average = 0;
        for (Rater r : myRaters) {
            if(r.hasRating(movieID)){
                count++;
                sum += r.getRating(movieID);
            }
        }
        if(count>=minimalRaters){
            average = sum/count;    
        }else{
            average = 0.0;
        }
        return average;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        int raterCount = 0;
        for(String m:movies){
            Double avg = getAverageByID(m,minimalRaters);
            if(avg>0.0){
                Rating r = new Rating(m,avg);
                list.add(r);
            }
        }
        return list;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);  
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String m:movies){
            Double avg = getAverageByID(m,minimalRaters);
            if(avg>0.0){
                Rating r = new Rating(m,avg);
                list.add(r);
            }
        }
        return list;
    }
    private double dotProduct(Rater me, Rater r){
        //System.out.println(me+" "+me.numRatings()+" || "+r+" "+r.numRatings());
        ArrayList<String> meItems =  me.getItemsRated();
        ArrayList<String> rItems =  r.getItemsRated();
        double runningTotal = 0;
        for(String s : meItems){
            if(me.hasRating(s) && r.hasRating(s)){
                double meRating = me.getRating(s)-5;    
                double rRating = r.getRating(s)-5;
                runningTotal += meRating*rRating;
            }
        }
        return runningTotal;
    }
    private ArrayList<Rating> getSimilarities(String raterID){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        for(Rater r : myRaters){
            if(!raterID.equals(r.getID())){
                double value = dotProduct(RaterDatabase.getRater(raterID),r);
                if(value > 0){
                    similarities.add(new Rating(r.getID(),value));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
	    for (String movieID : movies){
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
        	}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
}
