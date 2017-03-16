package view;


/**
 * View Interface.
 */

public interface View {
    /**
     * Update the view.
     */
    void update();

    /**
     * Show an error message.
     * @param message message to show
     */
    void errorMessage(String message);
}

