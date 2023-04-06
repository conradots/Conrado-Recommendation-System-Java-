/**
 * The Rater interface.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public interface Rater {
    public void addRating(String item, double rating);
    
    public boolean hasRating(String item);
    
    public String getID();
    
    public double getRating(String item);
    
    public int numRatings();
    
    public ArrayList<String> getItemsRated();
}