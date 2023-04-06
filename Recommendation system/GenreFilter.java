/**
 * Filter by genre. Only movies of a specific genre are chosen
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}