package view;

import java.util.Map;
import model.Lot;

/**
 * View Interface.
 */

public interface View {

    /**
     * Called to advice possible discounts.
     * 
     * @param lots lots
     */
    void discountAdvice(final Map<Lot, Integer> lots);

    /**
     * Refresh the list of lots.
     * @param text text to refresh
     */
    void setTextInArea(final String text);
    /**
     * update the view.
     */
    void update();

}
