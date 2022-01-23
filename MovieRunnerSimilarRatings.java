import java.util.*;

public class MovieRunnerSimilarRatings{
    public void printAverageRatings(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        int minimalRaters = 35;
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        ArrayList<Rating> ratings = thrRat.getAverageRatings(minimalRaters);
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(year));
        af.addFilter(new GenreFilter(genre));
        
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,af);
        
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+MovieDatabase.getGenres(r.getItem()));
        }    
    }
    public void printSimilarRatings(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 3;
        int topSimRaters = 10;
        String ratID = "337";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        RaterDatabase.initialize(sourceRat);
        int ratSize = RaterDatabase.size();
        FourthRatings fthRat = new FourthRatings();    
        
         ArrayList<Rating> ratings = fthRat.getSimilarRatings(ratID, topSimRaters, minimalRaters);
        // System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        // System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            // ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
        // for(Rating r : ratings){
            // System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        // }
    }
    public void printSimilarRatingsByGenre(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 5;
        int topSimRaters = 20;
        String ratID = "964";
        String genre = "Mystery";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        RaterDatabase.initialize(sourceRat);
        int ratSize = RaterDatabase.size();
        FourthRatings fthRat = new FourthRatings(); 
        GenreFilter filter = new GenreFilter(genre);
        
        ArrayList<Rating> ratings = fthRat.getSimilarRatingsByFilter(ratID, topSimRaters, minimalRaters,filter);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByDirector(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 2;
        int topSimRaters = 10;
        String ratID = "120";
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        RaterDatabase.initialize(sourceRat);
        int ratSize = RaterDatabase.size();
        FourthRatings fthRat = new FourthRatings(); 
        DirectorsFilter filter = new DirectorsFilter(directors);
        
        ArrayList<Rating> ratings = fthRat.getSimilarRatingsByFilter(ratID, topSimRaters, minimalRaters,filter);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 3;
        int topSimRaters = 10;
        String ratID = "168";
        String genre = "Drama";
        int min = 80;
        int max = 160;
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        RaterDatabase.initialize(sourceRat);
        int ratSize = RaterDatabase.size();
        FourthRatings fthRat = new FourthRatings(); 
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter(genre));
        filter.addFilter(new MinutesFilter(min,max));
        
        ArrayList<Rating> ratings = fthRat.getSimilarRatingsByFilter(ratID, topSimRaters, minimalRaters,filter);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printSimilarRatingsByYearMinutes(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 5;
        int topSimRaters = 10;
        String ratID = "314";
        int year = 1975;
        int min = 70;
        int max = 200;
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        RaterDatabase.initialize(sourceRat);
        int ratSize = RaterDatabase.size();
        FourthRatings fthRat = new FourthRatings(); 
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(year));
        filter.addFilter(new MinutesFilter(min,max));
        
        ArrayList<Rating> ratings = fthRat.getSimilarRatingsByFilter(ratID, topSimRaters, minimalRaters,filter);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" , "+MovieDatabase.getTitle(r.getItem()));
        }
    }
}
