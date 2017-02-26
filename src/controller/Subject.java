package controller;

public interface Subject {

    public void attach(Observer o);
    
    public void unattach(Observer o);
    
    public void notifyObserver();
}
