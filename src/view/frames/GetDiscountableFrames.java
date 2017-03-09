package view.frames;

import controller.Controller;
import model.discountstrategies.DiscountStrategy;

/**
 *
 */
public class GetDiscountableFrames extends DiscountableFrames {

    /**
     * 
     */
    private static final long serialVersionUID = 8659621781752305151L;
/**
 * 
 * @param controller controller
 * @param ds discount strategy
 */
    public GetDiscountableFrames(final Controller controller, final DiscountStrategy ds) {
        super(controller, ds);
    }

}