/**
 * The POJO class Rating stores the data about one rating of an item.
 * 
 * @author Duke Software Team
 * @version July 18th, 2022
 */

// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating(String anItem, double aValue) {
        item = anItem; // movie id or rater id
        value = aValue; // value of the rating
    }

    // Returns item being rated
    public String getItem() {
        return item;
    }

    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue() {
        return value;
    }

    // Returns a string of all the rating information
    public String toString() {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}