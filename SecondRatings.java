import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fRat = new FirstRatings();
        myMovies = fRat.loadMovies(moviefile);
        myRaters = fRat.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> list = new ArrayList<Rating>();
        int raterCount = 0;
        for(Movie m:myMovies){
            Double avg = getAverageByID(m.getID(),minimalRaters);
            if(avg>0.0){
                Rating r = new Rating(m.getID(),avg);
                list.add(r);
            }
        }
        return list;
    }
    public String getTitle(String movieID){
        String title = "";
        for(Movie m : myMovies){
            if(m.getID().equals(movieID)){
                title = m.getTitle();        
                break;
            }
            else{
                title = "No title found.";
            }
        }
        return title;
    }
    public String getID(String title){
        String ID = "";
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){
                ID = m.getID();        
                break;
            }
            else{
                ID = "No ID found.";
            }
        }
        return ID;    
    }
}