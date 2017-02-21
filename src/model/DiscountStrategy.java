package model;

import java.util.List;
import java.util.Map;

/**
 * Interface with one method that suggests which items should be discounted.
 */
public interface DiscountStrategy {

    /**
     * Calculates which items should be discounted.
     * @param lots lots to be examined
     * @return Map of items that should be discounted and discount percentage
     */
    Map<Lot, Integer> suggestDiscounts(List<Lot> lots);

}
