package view;

import controller.Controller;
import model.discountstrategies.DiscountStrategy;
import view.frames.AbstractGetDiscountableFrames;
/**
 * 
 *
 */
public class GetDiscountableScanFrames extends AbstractGetDiscountableFrames {

    /**
     * 
     */
    private static final long serialVersionUID = -7233556495417492226L;
/**
 * 
 * @param controller controller
 * @param ds discount strategy
 */
    public GetDiscountableScanFrames(final Controller controller, final DiscountStrategy ds) {
        super(controller, ds);
    }

}
