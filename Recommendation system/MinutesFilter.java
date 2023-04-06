/**
 * Filter by minutes. Only movies whose running time is at least a specific amount of time are chosen.
 * 
 * @author Ginny Dang
 * @version July 18th, 2022
 */

public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes);
    }
}