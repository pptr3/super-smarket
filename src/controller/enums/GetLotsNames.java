package controller.enums;
/**
 * Enum for titles of Get Lots frames.
 *
 */
public enum GetLotsNames {
    /**
     * 
     */
    ALFABETICALLY_SORTED("Alfabetically sorted"),
    /**
     * 
     */
    ONLY_EXPIRING("Only expiring"),
    /**
     * 
     */
    ALL("All");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */

    private GetLotsNames(final String str) {
        this.name = str;
    }

    /**
     *
     * @return string containing the name of the day.
     */
    public String getName() {
        return this.name;
    }
}