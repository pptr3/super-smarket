package view.enums;
/**
 * Names for main Application.
 *
 */
public enum ApplicationsNames {
    /**
     * Title for GetDiscountable.
     */
    TITLE("Super Smarket");
    private final String name;

    /**
     * 
     * @param str
     *            the title to set
     */

    private ApplicationsNames(final String str) {
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
