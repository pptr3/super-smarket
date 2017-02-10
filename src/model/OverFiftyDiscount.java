package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Discount strategy that suggests all the products that are over 50% of their time without
 * having sold 50% or more of the quantity to be put in discount.
 */
public class OverFiftyDiscount implements DiscountStrategy {

    @Override
    public Map<Lot, Integer> suggestDiscounts(List<Lot> lots) {
        Map<Lot, Integer> m = new HashMap<>();
        List<Lot> qualifiedLots;
        qualifiedLots = lots.stream().filter(l -> l.getExpirationDate().isPresent())
                        .filter(l -> 
                                    (l.getCurrentQuantity())
                                    > 
                                    (l.getInitialQuantity() / 2)
                        )
                        .filter(l -> 
                        (MyCustomDateImpl.today().getDifferenceInDays(l.getCheckInDate()))
                        >
                        (l.getExpirationDate().get().getDifferenceInDays(l.getCheckInDate()) / 2)
                        )
                        .collect(Collectors.toList());
        qualifiedLots.forEach(l -> m.put(l, 50));
        return m;
    }

}
