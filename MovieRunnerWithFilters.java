import java.util.*;

public class MovieRunnerWithFilters{
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
    public void printAverageRatingsByYear(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 20;
        int year = 2000;
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        YearAfterFilter yaFilter = new YearAfterFilter(year);
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,yaFilter);
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+
                MovieDatabase.getTitle(r.getItem()));
        }    
    }
    public void printAverageRatingsByGenre(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 20;
        String genre = "Comedy";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        GenreFilter geFilter = new GenreFilter(genre);
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,geFilter);
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+"\n\t"+
                MovieDatabase.getGenres(r.getItem()));
        }    
    }
    public void printAverageRatingsByMinutes(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 5;
        int min = 105;
        int max = 135;
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        MinutesFilter miFilter = new MinutesFilter(min,max);
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,miFilter);
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" Time:"+MovieDatabase.getMinutes(r.getItem())+" "+
                MovieDatabase.getTitle(r.getItem()));
        }    
    }
    public void printAverageRatingsByDirectors(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        DirectorsFilter diFilter = new DirectorsFilter(directors);
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,diFilter);
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+MovieDatabase.getDirector(r.getItem()));
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
    public void printAverageRatingsByDirectorsAndMinutes(){
        String sourceRat = "ratings.csv";
        String movFile = "ratedmoviesfull.csv";
        int minimalRaters = 3;
        int min = 90;
        int max = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        
        MovieDatabase.initialize(movFile);
        int numMovies = MovieDatabase.size();
        ThirdRatings thrRat = new ThirdRatings("data/"+sourceRat);    
        int ratSize = thrRat.getRaterSize();
        
        AllFilters af = new AllFilters();
        af.addFilter(new DirectorsFilter(directors));
        af.addFilter(new MinutesFilter(min,max));
        ArrayList<Rating> ratings = thrRat.getAverageRatingsByFilter(minimalRaters,af);
        
        System.out.println("Number of movies/raters: "+numMovies+" / "+ratSize);
        System.out.println("Number of movies w/ minimal ratings("+minimalRaters+"): "+
            ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+
                "\n\t"+MovieDatabase.getDirector(r.getItem()));
        }    
    }
}
