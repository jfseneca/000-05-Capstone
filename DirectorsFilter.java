import java.util.*;

public class DirectorsFilter implements Filter {
    //private String[] myDirectors;
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        //myDirectors = directors.split(",");
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        // System.out.println("+"+MovieDatabase.getDirector(id));
        // for(String s : myDirectors){
            // if(MovieDatabase.getDirector(id).indexOf(s)>-1){
                // System.out.println(s);
                // return true;
                
            // }
        // }
        // return false;
        //return MovieDatabase.getDirector(id).indexOf(myGenre)>-1;
        return myDirectors.indexOf(MovieDatabase.getDirector(id))>-1;
    }

}
