package controller;

/**
 *Subject it's an utility Interface for Observer pattern
 */

public interface Subject {

    /**
     * register an Observer
     * 
     * @param o
     */
    public void register(Observer o);

    /**
     * delete ad Observer
     * 
     * @param o
     */
    public void unregister(Observer o);

    /**
     * notify all Observers
     */
    public void notifyObserver();
}
