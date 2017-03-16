package controller;

import view.View;

/**
 * Subject interface for Observer pattern.
 *
 */
public interface Subject {

    /**
     * Attach a viewer Observer.
     * @param view view to attach
     */
    void attachView(View view);

    /**
     * Update the observers.
     */
    void updateView();

    /**
     * Show message error.
     * @param message error message to show
     */
    void showMessageErrorView(String message);
}
