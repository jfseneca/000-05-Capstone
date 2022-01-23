import java.util.*;

public class MovieRunnerAverage{
    public void printAverageRatings(){
        String sourceMov = "data/ratedmoviesfull.csv";
        String sourceRat = "data/ratings.csv";
        int minimalRaters = 12;
        SecondRatings secRat = new SecondRatings(sourceMov,sourceRat);    
        int movSize = secRat.getMovieSize();
        int ratSize = secRat.getRaterSize();
        ArrayList<Rating> ratings = secRat.getAverageRatings(minimalRaters);
        System.out.println("Number of movies/raters: "+movSize+"/"+ratSize);
        // for(int i = 0; i<ratings.size();i++){
            // if(!(i+1>=ratings.size())){
                // System.out.println(ratings.get(i).compareTo(ratings.get(i+1)));
            // }
        // }
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+secRat.getTitle(r.getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        String sourceMov = "data/ratedmoviesfull.csv";
        String sourceRat = "data/ratings.csv";
        int minimalRaters = 0;
        String title = "Vacation";
        SecondRatings secRat = new SecondRatings(sourceMov,sourceRat);    
        double rating = secRat.getAverageByID(secRat.getID(title),minimalRaters);
        System.out.println(title+" , "+rating);
    }

}
