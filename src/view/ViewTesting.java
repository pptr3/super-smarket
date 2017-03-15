package view;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
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

//CHECKSTYLE:OFF

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewTesting {

    @Test
    public void A_Test() {
        final Model m = new Warehouse();
        final Controller c = new ControllerImpl(m);
        final View v = new ViewImpl(c);
        c.registerView(v);
        try {
            v.errorMessage(null);
        } catch(Exception e) {
            assertFalse(e.getMessage() != "");
        }
        try {
            v.errorMessage("Test");
        } catch(Exception e) {
            assertTrue(e.getMessage() == "Test");
        }
    
    }
        //continuare a fare test della view, andare indietro nella linea dei commit e ripredere il test per il controller  
}
