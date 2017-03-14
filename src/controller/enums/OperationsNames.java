package controller.enums;

/**
 * Enum for titles of Operations frames.
 *
 */
public enum OperationsNames {
    /**
     * 
     */
    ADD_LOT("Add lot"),
    /**
     * 
     */
    SAVE("Save"),
    /**
     * 
     */
    LOAD_("Load"),
    /**
     * 
     */
    RESET_SUGGESTIONS("Reset suggestions"),
    /**
     * 
     */
    CONFIRM("Confirm"),
    /**
     * 
     */
    BACK("Back"),
    /**
     * 
     */
    SET_ON_SALE("Set on sale"),
    /**
     * 
     */
    PERCENTAGE("%"),
    /**
     * 
     */
    DONT_SUGGEST_ANYMORE("Don't suggest anymore"),
    /**
     * 
     */
    REMOVE_FROM_SALE("Remove from sale"),
    /**
     * 
     */
    REMOVE_LOT("Remove");

    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */
    private OperationsNames(final String str) {
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
