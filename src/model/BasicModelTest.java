package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicModelTest {

    Lot l = null;
    Lot p = null;
      
    @Test
    public void A_lotBuilderTest() {
        
        buildMilk();
        buildPasta();

        assertEquals(0, l.getId());
        assertEquals(1, p.getId());
        assertFalse(p.isOnSale());
        assertEquals(new MyCustomDateImpl(2017,2,8),p.getCheckInDate());
        assertEquals(Optional.empty(),p.getExpirationDate());
        assertEquals(60,p.getPricePerSingleItem());
        assertEquals(72,p.getInitialQuantity());
        assertEquals(72,p.getCurrentQuantity());
    }


    
    @Test
    public void B_removeLotTest() {
        buildMilk();
        
        Model m = new Warehouse();
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 20);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 16);
        assertEquals(0, m.getList(null).size());
    }

    @Test
    public void C_setOnSaleTest() {
        buildMilk();
        
        Model m = new Warehouse();
        m.addLotto(l);
        assertFalse(l.isOnSale());
        m.setOnSale(l.getId(), 10);
        assertTrue(m.getList(null).get(0).isOnSale());
    }

    private void buildMilk() {
        l = new LotBuilder()
                .name("Milk - brand")
                .checkInDate(new MyCustomDateImpl(2017,2,3))
                .expirationDate(new MyCustomDateImpl(2017,2,13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
    }
    
    private void buildPasta() {
        p = new LotBuilder()
                .name("Pasta - brand2")
                .checkInDate(new MyCustomDateImpl(2017,2,8))
                .quantity(72)
                .pricePerSingleItem(60)
                .build();
    }
    
}
