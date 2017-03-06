package view;


/**
 * View Interface.
 */

public interface View {

    /**
     * Refresh the list of lots.
     * @param text text to refresh
     */
    void setTextInArea(final String text);

    /**
     * update the view.
     */
    void update();

}
