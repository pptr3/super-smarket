package view.enums;
/**
 * Enum for names of scan frame.
 *
 */
public enum ScanNames {
    /**
     * Title for GetDiscountable.
     */
    TITLE("Scan"),
    /**
     * 
     */
    START_SCAN("Start scan"),
    /**
     * 
     */
    STOP_SCAN("Stop scan");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */

    private ScanNames(final String str) {
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
