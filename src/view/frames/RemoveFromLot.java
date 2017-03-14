package view.frames;

import controller.Controller;

/**
 * 
 *
 */
public class RemoveFromLot extends BasicOperationsOnLots {
    /**
     * 
     */
    @Override
    public void operation(final Controller controller, final Integer id, final Integer quantity) {
        controller.removeFromLotto(id, quantity);
    }
}
