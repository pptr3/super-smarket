package controller;

public class ViewObserver extends ViewImpl implements Observer {

    public ViewObserver() {
        super();
    }
    
    @Override
    public void update() {
        System.out.println("I has been  notified");
    }
}
