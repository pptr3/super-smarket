package controller;

import model.Model;
import model.Warehouse;

public class Test {

    public static void main(String[] args) {
        Model model = new Warehouse();
        MyFakeView view = new ViewImpl();
        Controller controller = new ControllerImpl();
       // controller.startScan();

    }

}
