package view.frames;

import controller.Controller;
/**
 * 
 *
 */
public class SetOnSale extends BasicOperationsOnLots {
/**
 * 
 */
    @Override
    public void operation(final Controller controller, final Integer id, final Integer quantity) {
        controller.setOnSale(id, quantity);
    }
}
