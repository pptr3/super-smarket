package model;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;

public class BasicModelTest {

    @Test
    public void test() {
        Model m = new Warehouse(); 
        Lot l = new LotImpl(0,"Latte a", new Date(2017,2,3), Optional.of(new Date(2017,2,13)), 30, 60 );
        assertEquals(0, m.getList(null).size());
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
    }
    
    @Test
    public void lotBuilderTest() {
        Lot l = new LotBuilder()
                .name("Milk - brand")
                .checkInDate(new Date(2017,2,3))
                .expirationDate(new Date(2017,2,13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
        
        assertEquals(0, l.getId());
        assertFalse(l.isOnSale());
        assertEquals(new Date(2017,2,3),l.getCheckInDate());
        assertEquals(Optional.of(new Date(2017,2,13)),l.getExpirationDate());
        assertEquals(50,l.getPricePerSingleItem());
        assertEquals(36,l.getInitialQuantity());
        assertEquals(36,l.getCurrentQuantity());
    }
    
    @Test
    public void removeLotTest() {
        Lot l = new LotBuilder()
                .name("Milk - brand")
                .checkInDate(new Date(2017,2,3))
                .expirationDate(new Date(2017,2,13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
        Model m = new Warehouse();
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 20);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 16);
        assertEquals(0, m.getList(null).size());
        
        
    }

}
