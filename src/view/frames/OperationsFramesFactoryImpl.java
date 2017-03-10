package view.frames;

import java.util.List;

import controller.Controller;
import model.Lot;
import model.discountstrategies.DiscountStrategy;

/**
 * Basic implementation of DiscountStrategyFactory, with all the discount strategies of the model.
 */
public class OperationsFramesFactoryImpl implements OperationsFramesFactory {

    @Override
    public OperationsFrames getListOfLots(final Controller controller, final List<Lot> lot) {
        return new OperationsFrames(controller, lot);
    }

    @Override
    public OperationsFrames getDiscountableListOfLots(final Controller controller, final DiscountStrategy ds) {
        return new OperationsFrames(controller, ds);
    }

}
