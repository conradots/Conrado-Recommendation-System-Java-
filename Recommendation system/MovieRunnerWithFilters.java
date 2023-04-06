/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 35;
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatings(minNumRatings);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println("found " + avgRatings.size() + " movie that have " + minNumRatings + " or more ratings");
        } else {
            System.out.println("found " + avgRatings.size() + " movies that have " + minNumRatings + " or more ratings");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            String title = movie.getTitle(id);
            System.out.println(value + " " + title);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByYearAfter() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 20;
        int queryYear = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(queryYear);
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, yearFilter);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println("found " + avgRatings.size() + " movie that have " + minNumRatings + " or more ratings (filter year: " + queryYear + ")");
        } else {
            System.out.println("found " + avgRatings.size() + " movies that have " + minNumRatings + " or more ratings (filter year: " + queryYear + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            int year = movie.getYear(id);
            String title = movie.getTitle(id);
            System.out.println(value + " " + year + " " + title);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByGenre() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 20;
        //String queryGenre = "Crime";
        String queryGenre = "Comedy";
        GenreFilter genreFilter = new GenreFilter(queryGenre);
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, genreFilter);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println("found " + avgRatings.size() + " movie that have " + minNumRatings + " or more ratings (filter genre: " + queryGenre + ")");
        } else {
            System.out.println("found " + avgRatings.size() + " movies that have " + minNumRatings + " or more ratings (filter genre: " + queryGenre + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            String genres = movie.getGenres(id);
            String title = movie.getTitle(id);
            System.out.println(value + " " + title);
            System.out.println("    " + genres);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 5;
        //int min = 110;
        int min = 105;
        //int max = 170;
        int max = 135;
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, minutesFilter);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println("found " + avgRatings.size() + " movie that have " + minNumRatings + " or more ratings (Filter minutes: " + min + " - " + max + ")");
        } else {
            System.out.println("found " + avgRatings.size() + " movies that have " + minNumRatings + " or more ratings (Filter minutes: " + min + " - " + max + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            int minutes = movie.getMinutes(id);
            String title = movie.getTitle(id);
            System.out.println(value + " Time: " + minutes + " " + title);
            //System.out.println("    " + genres);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByDirectors() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 4;
        //String queryDirector = "Charles Chaplin,Michael Mann,Spike Jonze";
        String queryDirectors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        String[] listQueryDirectors = queryDirectors.split("\\s*,");
        /*
        for (int i = 0; i < listQueryDirectors.length; i++) {
            System.out.println(listQueryDirectors[i]);
        }
        */
        DirectorsFilter directorsFilter = new DirectorsFilter(listQueryDirectors);
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, directorsFilter);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println("found " + avgRatings.size() + " movie that have " + minNumRatings + " or more ratings (filter directors: " + queryDirectors + ")");
        } else {
            System.out.println("found " + avgRatings.size() + " movies that have " + minNumRatings + " or more ratings (filter directors: " + queryDirectors + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            String directors = movie.getDirector(id);
            String title = movie.getTitle(id);
            System.out.println(value + " " + title);
            System.out.println("    " + directors);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        AllFilters filters = new AllFilters();
        //int minNumRatings = 1;
        int minNumRatings = 8;
        //int queryYear = 1980;
        int queryYear = 1990;
        //String queryGenre = "Romance";
        String queryGenre = "Drama";
        filters.addFilter(new YearAfterFilter(queryYear));
        filters.addFilter(new GenreFilter(queryGenre));
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, filters);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println(avgRatings.size() + " movie matched (minimum number of ratings: " + minNumRatings + ", filter year: " + queryYear + ", filter genre: " + queryGenre + ")");
        } else {
            System.out.println(avgRatings.size() + " movies matched (minimum number of ratings: " + minNumRatings + ", filter year: " + queryYear + ", filter genre: " + queryGenre + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            int year = movie.getYear(id);
            String title = movie.getTitle(id);
            String genres = movie.getGenres(id);
            System.out.println(value + " " + year + " " + title);
            System.out.println("    " + genres);
        }
        System.out.println("\n");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + thirdRatings.getRaterSize() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        AllFilters filters = new AllFilters();
        //int minNumRatings = 1;
        int minNumRatings = 3;
        //int queryMin = 30;
        int queryMin = 90;
        //int queryMax = 170;
        int queryMax = 180;
        //String queryDirectors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        String queryDirectors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        String[] listQueryDirectors = queryDirectors.split("\\s*,");
        filters.addFilter(new DirectorsFilter(listQueryDirectors));
        filters.addFilter(new MinutesFilter(queryMin, queryMax));
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumRatings, filters);
        if (avgRatings.size() == 1 || avgRatings.size() == 0) {
            System.out.println(avgRatings.size() + " movie matched (minimum number of ratings: " + minNumRatings + ", filter directors: " + queryDirectors + ", time range: " + queryMin + " - " + queryMax + ")");
        } else {
            System.out.println(avgRatings.size() + " movies matched (minimum number of ratings: " + minNumRatings + ", filter directors: " + queryDirectors + ", time range: " + queryMin + " - " + queryMax + ")");
        }
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            double value = rating.getValue();
            String id = rating.getItem();
            int time = movie.getMinutes(id);
            String title = movie.getTitle(id);
            String directors = movie.getDirector(id);
            System.out.println(value + " Time: " + time + " " + title);
            System.out.println("    " + directors);
        }
        System.out.println("\n");
    }
}