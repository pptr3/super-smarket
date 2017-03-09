package view.frames;

import java.util.List;

import controller.Controller;
import model.Lot;
/**
 * 
 *
 */
public class GetLotsFrame extends DiscountableFrames {

    /**
     * 
     */
    private static final long serialVersionUID = 2765590610269144542L;
/**
 * 
 * @param controller controller
 * @param lots list of lots
 */
    public GetLotsFrame(final Controller controller, final List<Lot> lots) {
        super(controller, lots);
    }
}



