package model;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

public class BasicModelTest {

    Lot l = null;
    Lot p = null;
    
    public BasicModelTest() {
            
        l = new LotBuilder()
                    .name("Milk - brand")
                    .checkInDate(new Date(2017,2,3))
                    .expirationDate(new Date(2017,2,13))
                    .quantity(36)
                    .pricePerSingleItem(50)
                    .build();
            
        
        p = new LotBuilder()
                    .name("Pasta - brand2")
                    .checkInDate(new Date(2017,2,8))
                    .quantity(72)
                    .pricePerSingleItem(60)
                    .build();
     
    }
      
    @Test
    public void lotBuilderTest() {
        
        assertEquals(1, p.getId());
        assertFalse(p.isOnSale());
        assertEquals(new Date(2017,2,8),p.getCheckInDate());
        assertEquals(Optional.of(null),l.getExpirationDate());
        assertEquals(60,l.getPricePerSingleItem());
        assertEquals(72,l.getInitialQuantity());
        assertEquals(72,l.getCurrentQuantity());
    }
    
    @Test
    public void removeLotTest() {
        Model m = new Warehouse();
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 20);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 16);
        assertEquals(0, m.getList(null).size());
    }

    @Test
    public void setOnSaleTest() {
        Model m = new Warehouse();
        m.addLotto(l);
        assertFalse(l.isOnSale());
        m.setOnSale(l.getId(), 10);
        assertTrue(l.isOnSale());
    }

    
}
