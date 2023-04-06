/**
 * The class MovieDatabase is an efficient way to get information about movies.
 * 
 * @author Duke Software Team
 * @version July 18th, 2022
 */

import java.util.ArrayList;

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}