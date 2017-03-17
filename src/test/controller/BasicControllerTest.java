package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import controller.Controller;
import controller.ControllerImpl;
import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;
import model.Warehouse;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import model.modifylists.ModifyListFactoryImpl;

//CHECKSTYLE:OFF
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BasicControllerTest {

    Lot lot;
    Lot lot2;

    @org.junit.Test
    public void A_testOneLot() {

        buildMilk();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLot(lot);
        assertEquals(controller.getList(null).size(), 1);
        controller.removeFromLot(lot.getId(), 36);
        assertEquals(controller.getList(null).size(), 0);
        controller.addLot(lot);
        assertEquals(lot.getCurrentQuantity(), 36);
        controller.removeFromLot(lot.getId(), 1);
        assertEquals(controller.getList(null).size(), 1);
        assertEquals(controller.getList(null).get(0).getCurrentQuantity(), 35);
        controller.removeFromLot(lot.getId(), 10);
        assertEquals(controller.getList(null).get(lot.getId()).getCurrentQuantity(), 25);
        controller.removeFromLot(lot.getId(), 24);
        assertEquals(controller.getList(null).get(lot.getId()).getCurrentQuantity(), 1);
        controller.removeFromLot(lot.getId(), 1);
        assertEquals(controller.getList(null).size(), 0);

    }

    @org.junit.Test
    public void B_testMoreLots() {

        buildMilk();
        buildPasta();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLot(lot);
        controller.addLot(lot);
        controller.addLot(lot2);
        controller.addLot(lot2);
        // Testing the removal of product about various lots
        assertEquals(controller.getList(null).size(), 4);
        controller.removeFromLot(controller.getList(null).get(0).getId(), 10);
        assertEquals(controller.getList(null).get(0).getCurrentQuantity(), 36);
        assertEquals(controller.getList(null).get(1).getCurrentQuantity(), 36); 
       }

    @org.junit.Test
    public void C_testSetOnSale() {

        buildPasta();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLot(lot2);
        assertEquals(controller.getList(null).size(), 1);
        assertEquals(controller.getList(null).get(0).getId(), 3);
        assertFalse(controller.getList(null).get(0).isOnSale());
        controller.setOnSale(lot2.getId(), 20);
        assertTrue(controller.getList(null).get(0).isOnSale());
        assertEquals(controller.getList(null).get(0).getSalePercentage(), 20);
        assertEquals(controller.getList(null).get(0).getPricePerSingleItem(), 60);
        assertEquals(controller.getList(null).get(0).getName(), "Pasta - brand2");
    }

    @org.junit.Test
    public void D_testRemoveFromSale() {

        buildPasta();
        buildMilk();
        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLot(lot);
        controller.setOnSale(0, 10);
        assertEquals(controller.getList(new ModifyListFactoryImpl().alphabeticalSorting()).size(), 1);
        assertEquals(controller.getList(new ModifyListFactoryImpl().onlyExpiring()).size(), 1);
        List<Lot> l = new ArrayList<>();
        l.add(controller.getList(null).get(0));
        controller.setOnSale(l.get(0).getId(), 90);
        assertEquals(l.size(), 1);
        assertTrue(l.get(0).isOnSale());
        assertEquals(l.get(0).getSalePercentage(), 90);
        controller.removeFromSale(l.get(0).getId());
        assertFalse(l.get(0).isOnSale());   
    }

    @org.junit.Test
    public void E_testOverFiftyDiscount() {

        Lot lot = new LotBuilder()
                .name("Eggs - brand2")
                .checkInDate(new MyCustomDateImpl(1017, 03, 15))
                .expirationDate(new MyCustomDateImpl(3017, 03, 16))
                .quantity(72)
                .pricePerSingleItem(60)
                .build();
    
        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLot(lot);
        Map<Lot, Integer> l = controller.getDiscountable(new DiscountStrategyFactoryImpl().overFiftyDiscount());
        assertEquals(l.size(), 1);
        
    }
    
    private void buildMilk() {
        lot = new LotBuilder().name("Milk - brand")
                .checkInDate(new MyCustomDateImpl(2017, 2, 3))
                .expirationDate(new MyCustomDateImpl(3017, 2, 13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
    }

        private void buildPasta() {
            lot2 = new LotBuilder()
                    .name("Pasta - brand2")
                    .checkInDate(new MyCustomDateImpl(3017, 2, 8))
                    .quantity(72)
                    .pricePerSingleItem(60)
                    .build();
        }
        
}