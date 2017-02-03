package model;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;

import org.junit.Test;

public class BasicModelTest {

    @Test
    public void test() {
        Model m = new Warehouse(); 
        Lot l = new LotImpl("Latte a", new Date(2017,2,3),new Date(2017,2,13), 30, 60 );
        assertEquals(0, m.getList(null).size());
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
        
    }

}
