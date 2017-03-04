package view;

import controller.Controller;
import controller.ControllerImpl;
import model.Model;
import model.Warehouse;

public class ApplicationTest {

    
    public static void main(String[] args) {
        Model m = new Warehouse();
        Controller c = new ControllerImpl(m);
        View v = new ViewImpl(c);
        c.registerView(v);
    }
}
