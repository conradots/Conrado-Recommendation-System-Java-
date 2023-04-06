/**
 * The ThirdRatings class does calculations focusing on computing averages on movie ratings.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    // default constructor
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double avgRating = 0.0;
        ArrayList<Rater> whoRated = new ArrayList<Rater>();
        int totalRating = 0;
        // Figure out how many raters have rated the movie with the given ID
        for (Rater rater : myRaters) {
            if (rater.getRating(id) != -1) {
                totalRating += rater.getRating(id);
                whoRated.add(rater);
            }
        }
        // Calculate avg rating if possible
        if (whoRated.size() >= minimalRaters) {
            avgRating = (double)totalRating / whoRated.size();
        }
        return avgRating;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // Find the average rating for every movie that has been rated by at least minimalRaters raters
        for (String id : movies) {
            double avgRating = getAverageByID(id, minimalRaters); // Avg rating points by at least minimalRaters raters
            if (avgRating != 0.0) {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        // Find the average rating for every movie that has been rated by at least minimalRaters raters
        for (String id : movies) {
            double avgRating = getAverageByID(id, minimalRaters);
            if (avgRating != 0.0) {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
}