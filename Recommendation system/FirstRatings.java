/**
 * The FirstRatings class processes the movie and ratings data.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }
        
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        HashMap<String, ArrayList<Rating>> raterIds = new HashMap<String, ArrayList<Rating>>();
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        
        // Gather all rater ids and corresponding ratings from the dataset
        for (CSVRecord record : parser) {
            String id = record.get("rater_id");
            String item = record.get("movie_id");
            double value = Double.parseDouble(record.get("rating"));
            
            if (!raterIds.containsKey(id)) {
                raterIds.put(id, new ArrayList<Rating>());
            }
            
            Rating rating = new Rating(item, value);
            raterIds.get(id).add(rating);
        }
        
        // Gather all raters from the hash map
        for (String raterId : raterIds.keySet()) {
            Rater newRater = new EfficientRater(raterId);
            ArrayList<Rating> ratings = raterIds.get(raterId);
            for (Rating r : ratings) {
                String i = r.getItem();
                double v = r.getValue();
                newRater.addRating(i, v);
            }
            raters.add(newRater);
        }
        
        return raters;
    }
    
    public void testLoadMovies() {
        //ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + movies.size());
        
        // Print all movies
        /*
        for (Movie movie : movies) {
            //System.out.println(movie.getID() + " | " + movie.getTitle() + " | " + movie.getYear() + " | " + movie.getCountry() + " | " + movie.getGenres() + " | " + movie.getDirector() + " | " + movie.getMinutes() + " | " + movie.getPoster());
            System.out.println(movie.toString());
            //System.out.println(movie);
        }
        System.out.println("\n");
        */
        
        // Determine how many movies include the Comedy genre
        String genre = "Comedy";
        int count = 0;
        
        for (Movie movie : movies) {
            String genres = movie.getGenres();
            if (genres.contains(genre)) {
                count += 1;
            } 
        }
        
        if (count == 1) {
            System.out.println(count + " movie includes the Comedy genre");
        } else {
            System.out.println(count + " movies include the Comedy genre");
        }
        
        System.out.println("\n");
        
        // Determine how many movies are greater than 150 minutes in length
        int minutes = 150;
        count = 0;
        
        for (Movie movie : movies) {
            int length = movie.getMinutes();
            if (length > minutes) {
                count += 1;
            }
        }
        
        if (count == 1) {
            System.out.println(count + " movie that is greater than 150 minutes in length");
        }
        else {
            System.out.println(count + " movies that are greater than 150 minutes in length");
        }
        
        System.out.println("\n");
        
        // Determine the maximum number of movies by any director, and who the directors are that directed that many movies
        HashMap<String, ArrayList<String>> directors = new HashMap<String, ArrayList<String>>();
        int maxNumOfMovies = 0;
        
        for (Movie movie : movies) {
            String[] currDirectors = movie.getDirector().split(",");
            for (String director : currDirectors) {
                if (!directors.containsKey(director)) {
                    ArrayList<String> directedMovies = new ArrayList<String>();
                    directedMovies.add(movie.getTitle());
                    directors.put(director, directedMovies);
                } else {
                    ArrayList<String> directedMovies = directors.get(director);
                    directedMovies.add(movie.getTitle());
                    directors.put(director, directedMovies);
                }
            }
        }
        
        for (String director : directors.keySet()) {
            int currNumOfMovies = directors.get(director).size();
            if (currNumOfMovies > maxNumOfMovies) {
                maxNumOfMovies = currNumOfMovies;
            }
        }
        
        System.out.println("The maximum number of movies by any director is " + maxNumOfMovies);
        
        for (String director : directors.keySet()) {
            int currNumOfMovies = directors.get(director).size();
            if (currNumOfMovies == maxNumOfMovies) {
                System.out.println(director + " directed " + directors.get(director));
            }
        }
        
        System.out.println("\n");
    }
    
    public void testLoadRaters() {
        // Gather all raters and movies rated by them
        //ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        HashMap<String, ArrayList<Rater>> movies = new HashMap<String, ArrayList<Rater>>();
        
        for (Rater rater : raters) {
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String i : itemsRated) {
                if (!movies.containsKey(i)) {
                    ArrayList<Rater> r = new ArrayList<Rater>();
                    movies.put(i, r);
                }
                movies.get(i).add(rater);
            }
        }
        
        // Print all raters
        System.out.println("Number of raters: " + raters.size());
        /*
        for (Rater rater : raters) {
            System.out.println(rater.getID() + " | " + rater.numRatings() + " | " + rater.getItemsRated());
        }
        
        System.out.println("\n");
        */
        
        // Find the number of ratings for a particular rater
        String id = "193";
        
        for (Rater rater : raters) {
            if (rater.getID().equals(id)) {
                System.out.println("Rater " + id + " has " + rater.numRatings() + " ratings");
                break;
            }
        }
        
        System.out.println("\n");
        
        // Find the maximum number of ratings by any rater
        int maxNumRatings = 0;
        ArrayList<Rater> ratersWithMaxNumRatings = new ArrayList<Rater>();
        
        for (Rater rater : raters) {
            int currNumRatings = rater.numRatings();
            if (currNumRatings > maxNumRatings) {
                maxNumRatings = currNumRatings;
            }
        }
        
        System.out.println("The maximum number of ratings by any rater is " + maxNumRatings);
        
        for (Rater rater : raters) {
            int currNumRatings = rater.numRatings();
            if (currNumRatings == maxNumRatings) {
                ratersWithMaxNumRatings.add(rater);
            }
        }
        
        if (ratersWithMaxNumRatings.size() == 1) {
            System.out.println("There is 1 rater with maximum number of ratings");
        } else {
            System.out.println("There are " + ratersWithMaxNumRatings.size() + " with maximum number of ratings");
        }
        
        for (Rater rater : ratersWithMaxNumRatings) {
            System.out.println("Rater " + rater.getID() + " rated " + rater.getItemsRated());
        }
        
        System.out.println("\n");
        
        // Find the number of ratings a particular movie has
        String movie = "1798709";
        
        System.out.println("The movie " + movie + " has been rated by " + movies.get(movie).size() + " raters");
        System.out.println("\n");
        
        // Determine how many different movies have been rated by all these raters
        if (movies.size() == 1) {
            System.out.println("1 movie has been rated");
        } else {
            System.out.println(movies.size() + " movies have been rated");
        }
        
        System.out.println("\n");
    }
}