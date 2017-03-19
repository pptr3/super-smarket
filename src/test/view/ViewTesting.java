package test.view;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import controller.Controller;
import controller.ControllerImpl;
import model.LotBuilder;
import model.Model;
import model.MyCustomDateImpl;
import model.Warehouse;
import model.resourcebundle.ResourceBound;
import view.View;
import view.ViewImpl;

//CHECKSTYLE:OFF

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewTesting {

    @Test
    public void A_TestException() throws InterruptedException {
        final Model m = new Warehouse();
        final Controller c = new ControllerImpl(m);
        final View v = new ViewImpl(c);
        c.registerView(v);
        try {
            new LotBuilder()
                .name("Carrots - brand 4")
                .expirationDate(new MyCustomDateImpl(LocalDate.now(), 4))
                .quantity(-20)
                .pricePerSingleItem(100)
                .build();
        } catch (Exception e) {
            assertEquals(e.getMessage(), new ResourceBound().setName("INVALID_INITIAL_QUANTITY"));
        }
       
        try {
            c.startScan();
            c.startScan();           
        } catch(Exception e) {
            assertEquals(e.getMessage(), null);
        }
        v.errorMessage("Super Smarket");
    }
       
}
