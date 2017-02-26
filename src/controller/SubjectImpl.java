package controller;

import java.util.List;

public class SubjectImpl implements Subject {

    private List<Observer> observers;
    
    
    @Override
    public void attach(Observer o) {
        this.observers.add(o); 
    }

    @Override
    public void unattach(Observer o) {
        this.observers.remove(this.observers.indexOf(o));
    }

    @Override
    public void notifyObserver() {
        this.observers.forEach(ob -> ob.update());
    }

}
