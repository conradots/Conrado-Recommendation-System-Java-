import java.util.*;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> itemIds = new ArrayList<String>();
        // Get movies released during or after 2010
        int year = 2010;
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        ArrayList<String> movies = MovieDatabase.filterBy(yearAfterFilter);
        // Only choose 20 items
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int random = rand.nextInt(movies.size());
            while (itemIds.contains(movies.get(random))) {
                random = rand.nextInt(movies.size());
            }
            itemIds.add(movies.get(i));
        }
        return itemIds;
    }
    
    /*
    @Override
    public void printRecommendationsFor(String webRaterID) {
        // Initialize
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        try{
            ArrayList<Rating> recommendations = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
            System.out.println("My weRaterID is " + webRaterID);
            System.out.println("recommendations was build?");
            System.out.println(recommendations.size());
            
            if (recommendations.size() < 10) {
                System.out.println("<h2>Sorry, there is no movie recommend for you based on your rating!</h2>");
            } else {
                ArrayList<Rating> outID = new ArrayList<>();
                for (int n = 0; n < 10; n++) {
                    outID.add(recommendations.get(n));
                }
            
                System.out.println("<style>");
                System.out.println("h2, h3 {");
                System.out.println("  text-align: center;");
                System.out.println("  height: 50px;");
                System.out.println("  line-height: 50px;");
                System.out.println("  font-family: Arial, Helvetica, sans-serif;");
                System.out.println("  background-color: #E0E0E0;");
                System.out.println("  color: #001A57;");
                System.out.println("}");
            
                System.out.println("table {");
                System.out.println("  border-collapse: collapse;");
                System.out.println("  margin: auto;");
                System.out.println("}");
            
                System.out.println("table, th, td {");
                System.out.println("  border: 2px solid white;");
                System.out.println("  font-size: 15px;");
                System.out.println("  padding: 2px 6px 2px 6px;");
                System.out.println("}");
                
                System.out.println("td img {");
                System.out.println("  display: block;");
                System.out.println("  margin-left: auto;");
                System.out.println("  margin-right: auto;");
                System.out.println("}");
            
                System.out.println("th {");
                System.out.println("  height: 40px;");
                System.out.println("  font-size: 18px;");
                System.out.println("  background-color: #E0E0E0;");
                System.out.println("  color: #001A57;");
                System.out.println("  text-align: center;");
                System.out.println("}");
            
                System.out.println("tr:nth-child(even) {");
                System.out.println("  background-color: #C4DDFF;");
                System.out.println("}");
            
                System.out.println("tr:nth-child(odd) {");
                System.out.println("  background-color: #7FB5FF;");
                System.out.println("}");
            
                System.out.println("tr:hover {");
                System.out.println("  background-color: #205375;");
                System.out.println("  color:white;");
                System.out.println("}");
            
                System.out.println("table td:first-child {");
                System.out.println("  text-align: center;");
                System.out.println("}");
            
                System.out.println("tr {");
                System.out.println("  font-family: Arial, Helvetica, sans-serif;");
                System.out.println("}");
            
                System.out.println(".rating {");
                System.out.println("  color:#ff6600;");
                System.out.println("  padding: 0px 10px;");
                System.out.println("  font-weight: bold;");
                System.out.println("}");
                System.out.println("</style>");
            
                System.out.println("<h2>Movie Recommendations</h2>");
                System.out.println("<table id = \"rater\">");
                System.out.println("<tr>");
                System.out.println("<th>Rank</th>");
                System.out.println("<th>Poster</th>");
                System.out.println("<th>Title & Rating</th>");
                System.out.println("<th>Genre</th>");
                System.out.println("<th>Director</th>");
                System.out.println("<th>Country</th>");
                System.out.println("</tr>");
            
                int rank = 1;
                for (Rating r : outID) {
                    String id = r.getItem();
                    System.out.println("<tr><td>" + rank + "</td>" +
                        "<td><img src = \"" + MovieDatabase.getPoster(id) + "\" width=\"50\" height=\"70\"></td>" +
                        "<td>" + MovieDatabase.getYear(id) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" + id + "\">" 
                        + MovieDatabase.getTitle(id) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;" 
                        + String.format("%.1f", r.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(id) + "</td>" +
                        "<td>" + MovieDatabase.getDirector(id) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(id) + "</td>" +
                        "</tr>");
                    rank += 1;
                }
            }
            System.out.println("</table>");
            System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours.</h3>");
        } catch(Exception e) {
            System.out.println("I'm sorry, there's a systme error. Please go back and try again");
        }
    }
    */
    
    @Override
    public void printRecommendationsFor(String webRaterID) {
        // Initialize
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase movie = new MovieDatabase();
        RaterDatabase rater = new RaterDatabase();
        movie.initialize(moviefile);
        rater.initialize(ratingsfile);
        
        // Get recommendations
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> recommendations = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        // debug
        System.out.println("My weRaterID is " + webRaterID);
        System.out.println("recommendations was build?");
        System.out.println(recommendations.size());
        
        if (recommendations.size() < 10) {
            System.out.println("<h2>Sorry, there is no movie recommend for you based on your rating!</h2>");
        } else {
            ArrayList<Rating> outID = new ArrayList<>();
            for (int n = 0; n < 10; n++) {
                outID.add(recommendations.get(n));
            }
            
            System.out.println("<style>");
            System.out.println("h2, h3 {");
            System.out.println("  text-align: center;");
            System.out.println("  height: 50px;");
            System.out.println("  line-height: 50px;");
            System.out.println("  font-family: Arial, Helvetica, sans-serif;");
            System.out.println("  background-color: #E0E0E0;");
            System.out.println("  color: #001A57;");
            System.out.println("}");
            
            System.out.println("table {");
            System.out.println("  border-collapse: collapse;");
            System.out.println("  margin: auto;");
            System.out.println("}");
            
            System.out.println("table, th, td {");
            System.out.println("  border: 2px solid white;");
            System.out.println("  font-size: 15px;");
            System.out.println("  padding: 2px 6px 2px 6px;");
            System.out.println("}");
            
            System.out.println("td img {");
            System.out.println("  display: block;");
            System.out.println("  margin-left: auto;");
            System.out.println("  margin-right: auto;");
            System.out.println("}");
            
            System.out.println("th {");
            System.out.println("  height: 40px;");
            System.out.println("  font-size: 18px;");
            System.out.println("  background-color: #E0E0E0;");
            System.out.println("  color: #001A57;");
            System.out.println("  text-align: center;");
            System.out.println("}");
            
            System.out.println("tr:nth-child(even) {");
            System.out.println("  background-color: #C4DDFF;");
            System.out.println("}");
            
            System.out.println("tr:nth-child(odd) {");
            System.out.println("  background-color: #7FB5FF;");
            System.out.println("}");
            
            System.out.println("tr:hover {");
            System.out.println("  background-color: #205375;");
            System.out.println("  color:white;");
            System.out.println("}");
            
            System.out.println("table td:first-child {");
            System.out.println("  text-align: center;");
            System.out.println("}");
            
            System.out.println("tr {");
            System.out.println("  font-family: Arial, Helvetica, sans-serif;");
            System.out.println("}");
            
            System.out.println(".rating {");
            System.out.println("  color:#ff6600;");
            System.out.println("  padding: 0px 10px;");
            System.out.println("  font-weight: bold;");
            System.out.println("}");
            System.out.println("</style>");
            
            System.out.println("<h2>Movie Recommendations</h2>");
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");
            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Director</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");
            
            int rank = 1;
            for (Rating r : outID) {
                String id = r.getItem();
                System.out.println("<tr><td>" + rank + "</td>" +
                        "<td><img src = \"" + MovieDatabase.getPoster(id) + "\" width=\"50\" height=\"70\"></td>" +
                        "<td>" + MovieDatabase.getYear(id) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" + id + "\">" 
                        + MovieDatabase.getTitle(id) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;" 
                        + String.format("%.1f", r.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(id) + "</td>" +
                        "<td>" + MovieDatabase.getDirector(id) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(id) + "</td>" +
                        "</tr>");
                rank += 1;
            }
        }
        System.out.println("</table>");
        System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours.</h3>");
    }
}