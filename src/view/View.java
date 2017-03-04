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

    void setTextInArea(final String text);
    /**
     * update the view.
     */
    void update();

}
