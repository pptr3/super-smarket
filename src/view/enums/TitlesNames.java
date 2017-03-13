package view.enums;
/**
 * Names for main Application.
 *
 */
public enum TitlesNames {
    /**
     * Title for Application.
     */
    TITLE_APPLICATION("Super Smarket"),
    /**Title for Get Discountable.
     * 
     */
    GET_DISCOUNTABLE_TITLE("Get Discountable Lots"),
    /**
     * Title for Get Lots.
     */
    GET_LOTS_TITLE("Get Lots"),
    /**
     * Title for Operations.
     */
    OPERATIONS_TITLE("Operations"),
    /**
     * 
     */
    SCAN_TITLE("Scan");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */

    private TitlesNames(final String str) {
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
