package view.frames;

import java.util.List;

import controller.Controller;
import model.Lot;
import model.discountstrategies.DiscountStrategy;

/**
 * 
 * Factory that instantiates OperationsFrames objects.
 *
 */
public interface OperationsFramesFactory {

    /**
     * Returns the list of lots.
     * 
     * @param controller controller
     * @param lot list of lots
     * @return An OperationsFrames object
     */
    OperationsFrames getListOfLots(Controller controller, List<Lot> lot);

    /**
     * Returns the list of lots based on the discount strategy.
     * 
     * @param controller controller
     * @param ds discount strategy
     * @return An OperationsFrames object
     */
    OperationsFrames getDiscountableListOfLots(Controller controller, DiscountStrategy ds);
}