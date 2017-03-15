package controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import controller.Controller;
import controller.ControllerImpl;
import model.Lot;
import model.LotBuilder;
import model.Model;
import model.MyCustomDateImpl;
import model.Warehouse;
import model.discountstrategies.DiscountStrategy;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import model.modifylists.ModifyList;
import model.modifylists.ModifyListFactoryImpl;
import view.ViewImpl;
//CHECKSTYLE:OFF
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BasicControllerTest {

    Lot lot;
    Lot lot2;

    @org.junit.Test
    public void A_testOneLot() {

        buildMilk();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLotto(lot);
        assertEquals(controller.getList(null).size(), 1);
        controller.removeFromLotto(lot.getId(), 36);
        assertEquals(controller.getList(null).size(), 0);
        controller.addLotto(lot);
        assertEquals(lot.getCurrentQuantity(), 36);
        controller.removeFromLotto(lot.getId(), 1);
        assertEquals(controller.getList(null).size(), 1);
        assertEquals(controller.getList(null).get(0).getCurrentQuantity(), 35);
        controller.removeFromLotto(lot.getId(), 10);
        assertEquals(controller.getList(null).get(lot.getId()).getCurrentQuantity(), 25);
        controller.removeFromLotto(lot.getId(), 24);
        assertEquals(controller.getList(null).get(lot.getId()).getCurrentQuantity(), 1);
        controller.removeFromLotto(lot.getId(), 1);
        assertEquals(controller.getList(null).size(), 0);

    }

    @org.junit.Test
    public void B_testMoreLots() {

        buildMilk();
        buildPasta();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLotto(lot);
        controller.addLotto(lot);
        controller.addLotto(lot2);
        controller.addLotto(lot2);
        // Testing the removal of product about various lots
        assertEquals(controller.getList(null).size(), 4);
        controller.removeFromLotto(controller.getList(null).get(0).getId(), 10);
        assertEquals(controller.getList(null).get(0).getCurrentQuantity(), 36);
        assertEquals(controller.getList(null).get(1).getCurrentQuantity(), 36); 
        
    }

    @org.junit.Test
    public void C_testSetOnSale() {

        buildPasta();

        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLotto(lot2);
        assertEquals(controller.getList(null).size(), 1);
        assertEquals(controller.getList(null).get(0).getId(),3); //the ID changes
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
        controller.addLotto(lot);
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
                .expirationDate(new MyCustomDateImpl(2017, 03, 16))
                .quantity(72)
                .pricePerSingleItem(60)
                .build();
    
        Controller controller = new ControllerImpl(new Warehouse());
        controller.addLotto(lot);
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