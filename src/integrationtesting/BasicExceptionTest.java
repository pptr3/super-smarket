package integrationtesting;

//CHECKSTYLE:OFF

import org.junit.Test;

import model.Model;
import model.Warehouse;

public class BasicExceptionTest {


    /**
     * Checks that removing all the items from a lot causes that lot to be removed from the warehouse.
     */
    @Test(expected = IllegalArgumentException.class)
    public void B_removeLotTest() {
        final Model m = new Warehouse();
        m.removeFromLot(2, 20);
    }

    /**
     * Just tests that the lots correctly gets put on sale.
     */
    @Test(expected = IllegalArgumentException.class)
    public void C_setOnSaleTest() {
        final Model m = new Warehouse();
        m.setOnSale(2, 10);
    }
    
    /**
     * Tests the removeFromSale method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void CA_removeFromSaleTest() {
        final Model m = new Warehouse();
        m.removeFromSale(2);
    }

   

}