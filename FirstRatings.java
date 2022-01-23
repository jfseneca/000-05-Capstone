
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> list = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec :fr.getCSVParser(true)){
            Movie mv = new Movie(rec.get("id"), rec.get("title"), rec.get("year"),
                rec.get("genre"), rec.get("director"),rec.get("country"), rec.get("poster"),
                Integer.parseInt(rec.get("minutes")));
            list.add(mv);
        }
        return list;
    }
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> list = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec :fr.getCSVParser(true)){
            EfficientRater rv = new EfficientRater(rec.get("rater_id"));
            boolean flag = false;
            int index = 0;
            if(list.size()==0){
                rv.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                list.add(rv);
            }
            else{
                for(Rater r : list){
                    if(r.getID().equals(rv.getID())){
                        //If rv is already in list
                        flag = true;
                        break;
                    }
                    else{
                        index++;
                    }
                }
                if(flag==true){
                    list.get(index).addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                }
                else{
                    rv.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                    list.add(rv);
                }
            }
        }
        return list;
    }
    public void testLoadMovies(){
        //String source = "data/ratedmovies_short.csv";
        String source = "data/ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(source);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int countCom = 0;
        int countLen = 0;
        int threshold = 0;
        int countDir = 0;
        for(Movie m: list ){
            //System.out.println(m);
            if(m.getGenres().indexOf("Comedy")>-1){
                countCom++;
            }
            if(m.getMinutes()>150){
                countLen++;
            }
            String[] output = m.getDirector().split(",");
            for(int i = 0; i<output.length;i++){
                if(!map.containsKey(m.getDirector())){
                    map.put(output[i],1);
                }
                else{
                    int num = map.get(output[i]) + 1;
                    map.remove(output[i]);
                    map.put(output[i],num);
                }
            }
        }
        for (String s : map.keySet()) {
            if (map.get(s)>threshold){
                threshold = map.get(s);
            }
        }
        for (Integer i : map.values()) {
            if(i==threshold){
                countDir++;
                
            }
        }
        String director = "";
        for (String s : map.keySet()) {
            if (map.get(s)==threshold){
                threshold = map.get(s);
                director = s;
            }
        }
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Number of movies: "+list.size());
        System.out.println("Number of comedy movies: "+countCom);
        System.out.println("Number of movies greater than 150 min. in length: "+countLen);
        System.out.println("The max number of movies by any director is "+threshold);
        System.out.println("And there are "+countDir+" at that maximum. His name is "+director+".");
    }
    public void testLoadRaters(){
        //String source = "data/ratings_short.csv";
        String source = "data/ratings.csv";
        ArrayList<Rater> list = loadRaters(source);
        
        String id = "193";
        int ratCount = 0;
        for(Rater r : list){
            if(r.getID().equals(id)){
                System.out.println(r.getID());
                ArrayList<String> itemList = r.getItemsRated();
                for(String item : itemList ){
                    //System.out.println("\t"+item+" "+r.getRating(item));
                    ratCount++;
                }        
            }
        }
        String movieID = "1798709";
        int count=0;
        int maxCount = 0;
        ArrayList<String> movieList = new ArrayList<String>();
        movieList.add(movieID);
        for(Rater r : list){
            ArrayList<String> itemList = r.getItemsRated();
            for(String item : itemList ){
                if(item.equals(movieID)){
                    count++;
                }
                if(!movieList.contains(item)){
                    movieList.add(item);
                }
            }
            if(itemList.size()>maxCount){
                maxCount = itemList.size();
            }
        }
        String maxName = "";
        for(Rater r: list){
            ArrayList<String> itemList = r.getItemsRated();
            if(itemList.size()==maxCount){
                maxName = r.getID();    
            }
        }
        System.out.println("maxCount = "+maxCount);
        System.out.println("maxName = "+maxName);
        System.out.println(ratCount);
        System.out.println("For the movie ID "+movieID+", there were "+count+" raters.");
        System.out.println(movieList.size());
    }
}
