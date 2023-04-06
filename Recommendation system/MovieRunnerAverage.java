/**
 * The MovieRunnerAverage class.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("Total number of movies: " + secondRatings.getMovieSize());
        System.out.println("Total number of raters: " + secondRatings.getRaterSize());
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 3;
        //int minNumRatings = 50;
        //int minNumRatings = 20;
        int minNumRatings = 12;
        ArrayList<Rating> avgRatings = secondRatings.getAverageRatings(minNumRatings);
        System.out.println("Number of movies that have " + minNumRatings + " or more ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String title = secondRatings.getTitle(rating.getItem());
            System.out.println(value + " " + title);
        }
    }
    
    public void getAverageRatingOneMovie() {
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingsfile);
        
        // Print out the average ratings for a specific movie title
        //String queryTitle = "The Godfather";
        //String queryTitle = "The Maze Runner";
        //String queryTitle = "Moneyball";
        String queryTitle = "Vacation";
        boolean found = false;
        ArrayList<Rating> avgRatings = secondRatings.getAverageRatings(0);
        for (Rating rating : avgRatings) {
            String id = rating.getItem();
            String title = secondRatings.getTitle(id);
            if (title.equals(queryTitle)) {
                System.out.println(rating.getValue());
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Movie title not found");
        }
    }
}