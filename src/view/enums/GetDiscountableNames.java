package view.enums;
/**
 * Enum for titles of Get Discountable frames.
 *
 */
public enum GetDiscountableNames {
    /**
     * Title for GetDiscountable.
     */
    TITLE("Get Discountable Lots"),
    /**
     * 
     */
    OVER_FIFTY_DISCOUNT("Over fifty discount"),
    /**
     * 
     */
    EXPIRES_WITHIN_A_WEEK("Expires within a week");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */
    private GetDiscountableNames(final String str) {
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
