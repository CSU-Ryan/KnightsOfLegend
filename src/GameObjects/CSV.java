package GameObjects;

/**
 * For any objects expected to be able to have their data stored in a CSV file.
 */
public interface CSV {
    /**
     * returns a list of the object's data for storage in a CSV file.<br>
     * <br>
     * Items are typically separated by commas with no spaces.
     *
     * @return a string of the object's data
     */
    String toCSV();
}
