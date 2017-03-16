package view.frames;

import controller.Controller;

/**
 * Set a lot on sale.
 *
 */
public class SetOnSale extends BasicOperationsOnLots {
    /**
     * An operation that set on sale the lot with the specified with the given quantity.
     */
    @Override
    public void operation(final Controller controller, final Integer id, final Integer quantity) {
        controller.setOnSale(id, quantity);
    }
}
