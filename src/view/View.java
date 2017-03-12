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
     * @param message Message to show
     */
    void errorMessage(String message);
}

