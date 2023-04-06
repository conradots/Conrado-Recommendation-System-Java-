/**
 * Filter by year. Only movies released in or after a specific year are chosen
 * 
 * @author Duke Software Team
 * @version July 18th, 2022
 */

public class YearAfterFilter implements Filter {
    private int myYear;
    
    public YearAfterFilter(int year) {
        myYear = year;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
}