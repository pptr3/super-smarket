package controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import model.Lot;
import model.LotBuilder;
import model.LotWithActions;
import model.Model;
import model.Warehouse;

public class BasicControllerTest {
     
        @org.junit.Test
        public void testKeys() {
               Model model = new Warehouse();
               Controller controller = new ControllerImpl();
               List<Lot> lots = new ArrayList<>();
               Lot lot = new LotBuilder()
                       .name("Milk - brand")
                       .checkInDate(new Date(2017,2,3))
                       .expirationDate(new Date(2017,2,13))
                       .quantity(36)
                       .pricePerSingleItem(50)
                       .build();
               controller.addLotto(lot);
               assertEquals(controller.getList().size(),1);
               controller.addLotto(lot);
               assertEquals(controller.getList().size(),2);
               
        }
}