/**
 * Filter by genre. Only movies of a specific genre are chosen
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String[] directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] directors = new MovieDatabase().getDirector(id).split("\\s*,");
        for (int i = 0; i < directors.length; i++) {
            directors[i] = directors[i].trim();
            for (int j = 0; j < myDirectors.length; j++) {
                if (directors[i].equals(myDirectors[j])) {
                    return true;
                }
            }
        }
        return false;
    }
}