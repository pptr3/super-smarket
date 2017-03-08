package view;

import controller.Controller;
import controller.ControllerImpl;
import model.Model;
import model.Warehouse;
/**
 *
 */
//CHECKSTYLE:OFF
public class ApplicationTest {

    /**
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        Model m = new Warehouse();
        Controller c = new ControllerImpl(m);
        View v = new ViewImpl(c);
        c.registerView(v);
    }
}
