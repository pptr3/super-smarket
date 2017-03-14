package view.frames;

import javax.swing.JTextArea;
import controller.Controller;
import model.Lot;
/**
 * An abstract class used for Template Method.
 *
 */
public abstract class BasicOperationsOnLots {

/**
 * 
 * @param area area
 * @param text text
 */
    public void updateText(final JTextArea area, final String text) {
        area.setText(text);
    }
/**
 * 
 * @param controller controller
 * @param lot lot
 */
    public void dontSuggest(final Controller controller, final Lot lot) {
        controller.dontSuggestAnymore(lot);
    }
/**
 * 
 * @param controller controller
 * @param id id
 */
    public void removeFromSale(final Controller controller, final Integer id) {
        controller.removeFromSale(id);
    }
/**
 * 
 * @param controller controller
 * @param id id
 * @param quantity quantity
 */
    public abstract void operation(final Controller controller, final Integer id, final Integer quantity);

}
