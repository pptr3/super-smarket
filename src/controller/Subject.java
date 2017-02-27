package controller;
/**
 * Subject interface for Observer pattern.
 *
 */
public interface Subject {

    /**
     * Attach a viewer Observer.
     * @param view view to attach
     */
    void attachView(MyFakeView view);

    /**
     * Unattach a viewer Observer.
     */
    void unattachView();

    /**
     * update the observers.
     */
    void updateView();
}
