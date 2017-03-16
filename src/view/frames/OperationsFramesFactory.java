package view.frames;

import java.util.List;

import controller.Controller;
import model.Lot;
import model.discountstrategies.DiscountStrategy;

/**
 * 
 * Factory that instantiates OperationsFrames.
 *
 */
public interface OperationsFramesFactory {

    /**
     * Returns the list of lots.
     * 
     * @param controller controller
     * @param lot list of lots
     * @return an OperationsFrames
     */
    OperationsFrames getListOfLots(Controller controller, List<Lot> lot);

    /**
     * Returns the list of lots based on the discount strategy.
     * 
     * @param controller controller
     * @param ds discount strategy
     * @return an OperationsFrames
     */
    OperationsFrames getDiscountableListOfLots(Controller controller, DiscountStrategy ds);
}