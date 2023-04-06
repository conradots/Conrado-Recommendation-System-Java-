/**
 * The FourthRatings class does calculations focusing on computing averages on movie ratings.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        String moviefile = "ratedmovies_short.csv";
        //String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings_short.csv";
        //String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print a list of movies and their average ratings
        //int minNumRatings = 1;
        int minNumRatings = 35;
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatings(minNumRatings);
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
    
    public void printAverageRatingsByYearAfterAndGenre() {
        String moviefile = "ratedmovies_short.csv";
        //String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings_short.csv";
        //String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
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
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatingsByFilter(minNumRatings, filters);
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
    
    public void printSimilarRatings() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print movies with similar ratings
        //String queryRaterId = "65";
        //String queryRaterId = "337";
        String queryRaterId = "71";
        int minNumRaters = 5;
        //int minNumRaters = 3;
        int numTopSimilarRaters = 20;
        //int numTopSimilarRaters = 10;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(queryRaterId, numTopSimilarRaters, minNumRaters);
        String topRatedMovieId = similarRatings.get(0).getItem();
        String topRatedMovieTitle = movie.getTitle(topRatedMovieId);
        
        System.out.println("The movie with the top rated average is " + "“" + topRatedMovieTitle + "”");
        System.out.println();
        /*
        System.out.println("Recommended movies:");
        for (Rating rating : similarRatings) {
            String movieId = rating.getItem();
            String title = movie.getTitle(movieId);
            System.out.println(title);
        }
        */
    }
    
    public void printSimilarRatingsByGenre() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print movies with similar ratings
        //String queryGenre = "Action";
        String queryGenre = "Mystery";
        //String queryRaterId = "65";
        String queryRaterId = "964";
        int minNumRaters = 5;
        int numTopSimilarRaters = 20;
        GenreFilter genreFilter = new GenreFilter(queryGenre);
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(queryRaterId, numTopSimilarRaters, minNumRaters, genreFilter);
        String topRatedMovieId = similarRatings.get(0).getItem();
        String topRatedMovieTitle = movie.getTitle(topRatedMovieId);
        
        System.out.println("The movie with the top rated average of the " + queryGenre + " genre is " + "“" + topRatedMovieTitle + "”");
        System.out.println();
        System.out.println("Recommended movies:");
        for (Rating rating : similarRatings) {
            String movieId = rating.getItem();
            String title = movie.getTitle(movieId);
            String genres = movie.getGenres(movieId);
            System.out.println("Title: " + title + " | Rating: " + rating.getValue());
            System.out.println("    Genre(s): " + genres);
        }
    }
    
    public void printSimilarRatingsByDirector() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print movies with similar ratings
        //String queryDirectors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        String queryDirectors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        //String queryRaterId = "1034";
        String queryRaterId = "120";
        //int minNumRaters = 3;
        int minNumRaters = 2;
        int numTopSimilarRaters = 10;
        DirectorsFilter directorsFilter = new DirectorsFilter(queryDirectors.split("\\s*,"));
        //ArrayList<Rating> similarRatings = fourthRatings.getAverageRatingsByFilter(numTopSimilarRaters, directorsFilter);
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(queryRaterId, numTopSimilarRaters, minNumRaters, directorsFilter);
        String topRatedMovieId = similarRatings.get(0).getItem();
        String topRatedMovieTitle = movie.getTitle(topRatedMovieId);
        
        System.out.println("The movie with the top rated average by " + queryDirectors + " is " + "“" + topRatedMovieTitle + "”");
        System.out.println();
        System.out.println("Recommended movies:");
        for (Rating rating : similarRatings) {
            String movieId = rating.getItem();
            String title = movie.getTitle(movieId);
            String director = movie.getDirector(movieId);
            System.out.println("Title: " + title + " | Rating: " + rating.getValue());
            System.out.println("    Director(s): " +  director);
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print movies with similar ratings
        //String queryGenre = "Adventure";
        String queryGenre = "Drama";
        //int queryMin = 100;
        int queryMin = 80;
        //int queryMax = 200;
        int queryMax = 160;
        //String queryRaterId = "65";
        String queryRaterId = "168";
        //int minNumRaters = 5;
        int minNumRaters = 3;
        int numTopSimilarRaters = 10;
        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter(queryGenre));
        filters.addFilter(new MinutesFilter(queryMin, queryMax));
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(queryRaterId, numTopSimilarRaters, minNumRaters, filters);
        String topRatedMovieId = similarRatings.get(0).getItem();
        String topRatedMovieTitle = movie.getTitle(topRatedMovieId);
        
        System.out.println("The movie with the top rated average of the " + queryGenre + " genre and has the duration between " + queryMin + " and " + queryMax + " is " + "“" + topRatedMovieTitle + "”");
        System.out.println();
        System.out.println("Recommended movies:");
        for (Rating rating : similarRatings) {
            String movieId = rating.getItem();
            String title = movie.getTitle(movieId);
            int minutes = movie.getMinutes(movieId);
            String genres = movie.getGenres(movieId);
            System.out.println("Title: " + title + " | Minutes: " + minutes + " | Rating: " + rating.getValue());
            System.out.println("    Genre(s): " + genres);
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        //String moviefile = "ratedmovies_short.csv";
        String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "ratings_short.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Print total number of movies and total number of raters
        System.out.println("read data for " + rater.size() + " raters ");
        System.out.println("read data for " + movie.size() + " movies ");
        System.out.println("\n");
        
        // Print movies with similar ratings
        //int queryYear = 2000;
        int queryYear = 1975;
        //int queryMin = 80;
        int queryMin = 70;
        //int queryMax = 100;
        int queryMax = 200;
        //String queryRaterId = "65";
        String queryRaterId = "314";
        int minNumRaters = 5;
        int numTopSimilarRaters = 10;
        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(queryYear));
        filters.addFilter(new MinutesFilter(queryMin, queryMax));
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(queryRaterId, numTopSimilarRaters, minNumRaters, filters);
        String topRatedMovieId = similarRatings.get(0).getItem();
        String topRatedMovieTitle = movie.getTitle(topRatedMovieId);
        
        System.out.println("The movie with the top rated average released in or after " + queryYear + " and has the duration between " + queryMin + " and " + queryMax + " is " + "“" + topRatedMovieTitle + "”");
        System.out.println();
        System.out.println("Recommended movies:");
        for (Rating rating : similarRatings) {
            String movieId = rating.getItem();
            String title = movie.getTitle(movieId);
            int year = movie.getYear(movieId);
            int minutes = movie.getMinutes(movieId);
            System.out.println("Title: " + title + " | Year: " + year + " | Minutes: " + minutes + " | Rating: " + rating.getValue());
        }
    }
}