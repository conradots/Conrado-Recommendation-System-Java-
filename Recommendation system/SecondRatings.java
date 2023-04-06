/**
 * The SecondRatings class does calculations focusing on computing averages on movie ratings.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    // default constructor
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        // Find the average rating for every movie that has been rated by at least minimalRaters raters
        for (Movie movie : myMovies) {
            String id = movie.getID();
            double avgRating = getAverageByID(id, minimalRaters); // Avg rating points by at least minimalRaters raters
            if (avgRating != 0.0) {
                Rating rating = new Rating(id, avgRating);
                avgRatings.add(rating);
            }
        }
        return avgRatings;
    }
    
    public String getTitle(String id) {
        String title = "ID not found";
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                title = movie.getTitle();
                break;
            }
        }
        return title;
    }
    
    public String getID(String title) {
        String id = "Title not found";
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                id = movie.getID();
                break;
            }
        }
        return id;
    }
}