import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings fRat = new FirstRatings();
        myRaters = fRat.loadRaters(ratingsfile);
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public double getAverageByID(String movieID, int minimalRaters){
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
}