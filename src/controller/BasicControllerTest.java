package controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicControllerTest {

    Lot lot;
    Lot lot2;

    @org.junit.Test
    public void A_testOneLot() {

        buildMilk();

        Controller controller = new ControllerImpl();
        controller.addLotto(lot);
        assertEquals(controller.getList().size(), 1);
        controller.removeFromLotto(lot.getId(), 36);
        assertEquals(controller.getList().size(), 0);
        controller.addLotto(lot);
        assertEquals(lot.getCurrentQuantity(), 36);
        controller.removeFromLotto(lot.getId(), 1);
        assertEquals(controller.getList().size(), 1);
        assertEquals(controller.getList().get(0).getCurrentQuantity(), 35);
        controller.removeFromLotto(lot.getId(), 10);
        assertEquals(controller.getList().get(lot.getId()).getCurrentQuantity(), 25);
        controller.removeFromLotto(lot.getId(), 24);
        assertEquals(controller.getList().get(lot.getId()).getCurrentQuantity(), 1);
        controller.removeFromLotto(lot.getId(), 1);
        assertEquals(controller.getList().size(), 0);

    }

    @org.junit.Test
    public void B_testMoreLots() {

        buildMilk();
        buildPasta();

        Controller controller2 = new ControllerImpl();
        controller2.addLotto(lot);
        controller2.addLotto(lot);
        controller2.addLotto(lot2);
        controller2.addLotto(lot2);
        // Testing the removal of product about various lots
        assertEquals(controller2.getList().size(), 4);
        controller2.removeFromLotto(controller2.getList().get(0).getId(), 10);
        //FOR SHAPOUR
        assertEquals(controller2.getList().get(0).getCurrentQuantity(), 26);
        assertEquals(controller2.getList().get(1).getCurrentQuantity(), 26); // need to change the id number for lots with
        //the same product
        System.out.println(controller2.getList());
    }

    @org.junit.Test
    public void C_testSetOnSale() {

        buildPasta();

        Controller controller3 = new ControllerImpl();
        controller3.addLotto(lot2);
        assertEquals(controller3.getList().size(), 1);
        assertEquals(controller3.getList().get(0).getId(),3); //the ID changes
        assertFalse(controller3.getList().get(0).isOnSale());
        controller3.setOnSale(lot2.getId(), 20);
        assertTrue(controller3.getList().get(0).isOnSale());
        assertEquals(controller3.getList().get(0).getSalePercentage(), 20);
        assertEquals(controller3.getList().get(0).getPricePerSingleItem(), 60);
        assertEquals(controller3.getList().get(0).getName(), "Pasta - brand2");
    }
    
    private void buildMilk() {
        lot = new LotBuilder()
                .name("Milk - brand")
                .checkInDate(new MyCustomDateImpl(2017, 2, 3))
                .expirationDate(new MyCustomDateImpl(2017, 2, 13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
        }
        
        private void buildPasta() {
            lot2 = new LotBuilder()
                    .name("Pasta - brand2")
                    .checkInDate(new MyCustomDateImpl(2017, 2, 8))
                    .quantity(72)
                    .pricePerSingleItem(60)
                    .build();
        }
}