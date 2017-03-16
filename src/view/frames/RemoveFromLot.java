package view.frames;

import controller.Controller;

/**
 * Remove elements from a lot.
 *
 */
public class RemoveFromLot extends BasicOperationsOnLots {
    /**
     * An operation that remove from the lot with the specified id an amount specified by quantity.
     */
    @Override
    public void operation(final Controller controller, final Integer id, final Integer quantity) {
        controller.removeFromLot(id, quantity);
    }
}
