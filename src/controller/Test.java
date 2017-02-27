package controller;

import model.Model;
import model.Warehouse;

/**
 * Test Application.
 *
 */


public final class Test {

    private Test() { }
/**
 * 
 * @param args args
 */
    public static void main(final String[] args) {
        Model model = new Warehouse();
        Controller controller = new ControllerImpl(model);
        MyFakeView view = new ViewImpl(controller);
        controller.registerView(view);
    }

}
