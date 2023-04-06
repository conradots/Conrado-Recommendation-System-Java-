/**
 * The class EfficientRater keeps track of one rater and all their ratings.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings; // movie id and a rating associated with it

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }
    
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (hasRating(item) == true) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String item : myRatings.keySet()) {
            list.add(myRatings.get(item).getItem());
        }
        return list;
    }
}