package controller;

import model.Model;
import model.Warehouse;

public class Test {

    public static void main(String[] args) {
       Model model = new Warehouse();
       Controller controller = new ControllerImpl(model);
       MyFakeView view = new ViewImpl(controller);
       controller.registerView(view);
    }

}
