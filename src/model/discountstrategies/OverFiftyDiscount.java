package model.discountstrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Lot;
import model.MyCustomDateImpl;

/**
 * Discount strategy that suggests all the products that are over 50% of their time without
 * having sold 50% or more of the quantity to be put in discount.
 */
class OverFiftyDiscount implements DiscountStrategy {

    private final int discountAmount = 50;

    @Override
    public Map<Lot, Integer> suggestDiscounts(final List<Lot> lots) {
        final Map<Lot, Integer> m = new HashMap<>();
        List<Lot> qualifiedLots;
        qualifiedLots = lots.stream().filter(l -> l.getExpirationDate().isPresent())
                        .filter(l -> !l.isOnSale())
                        //only items that haven't sold 50% of the total amount yet
                        .filter(l -> 
                            (l.getCurrentQuantity())
                            > 
                            (l.getInitialQuantity() / 2)
                        )
                        //only items that have been in the warehouse for more than 50% of their lifetime
                        .filter(l -> 
                            (MyCustomDateImpl.today().getDifferenceInDays(l.getCheckInDate()))
                            >
                            (l.getExpirationDate().get().getDifferenceInDays(l.getCheckInDate()) / 2)
                        )
                        .collect(Collectors.toList());
        qualifiedLots.forEach(l -> m.put(l, discountAmount));
        return m;
    }

}
