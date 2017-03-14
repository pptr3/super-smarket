package controller.enums;
/**
 * 
 *
 */
public enum ErrorNames {
    /**
     * 
     */
    NO_LOTS("There is no lots."),
    /**
     * 
     */
    FORMAT("Something goes wrong..retry."
            + "\n" + "Fill all the fields and remember that the format of CheckInDate and ExpirationDate is yyyy-mm-dd."),
    /**
     * 
     */
    MISSING_NAME("Missing name."),
    /**
     * 
     */
    INVALID_INITIAL_QUANTITY("Invalid initial quantity."),
    /**
     * 
     */
    INVALID_PRICE_PER_ITEM("Invalid price per item"),
    /**
     * 
     */
    NOT_NEGATIVE_VALUE("Insert a not negative integer fewer than 100 please."),
    /**
     * 
     */
    NOT_ON_SALE("The lot was not on sale."),
    /**
     * 
     */
    ALREADY_ON_SALE("The lot is already on sale."),
    /**
     * 
     */
    CANT_REMOVE_ELEMENTS("Cannot remove more elements than the current quantity."),
    /**
     * 
     */
    ILLEGAL_FORMAT_FILE("Illegal file format."),
    /**
     * 
     */
    SOMETHING_GOES_WRONG("Something goes wrong."),
    /**
     * 
     */
    INVALID_EXPIRATION_DATE("Invalid expiration date.");
    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */
    private ErrorNames(final String str) {
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
