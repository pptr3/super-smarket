package application;

import controller.Controller;

import controller.ControllerImpl;
import model.Model;
import model.Warehouse;
import view.View;
import view.ViewImpl;
/**
 *
 */

public final class Application {

    private Application() { }

    /**
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        final Model m = new Warehouse();
        final Controller c = new ControllerImpl(m);
        final View v = new ViewImpl(c);
        c.registerView(v);
    }
}
