package controller.enums;
/**
 * 
 *
 */
public enum LotFeatures {
   /**
    * 
    */
    NAME("Name"),
    /**
     * 
     */
    CHECK_IN_DATE("Check in date"),
    /**
     * 
     */
    EXPIRATION_DATE("Expiration date"),
    /**
     * 
     */
    INITIAL_QUANTITY("Initial quantity"),
    /**
     * 
     */
    PRICE_PER_SINGLE_ITEM("Price per single item");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */
    private LotFeatures(final String str) {
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
