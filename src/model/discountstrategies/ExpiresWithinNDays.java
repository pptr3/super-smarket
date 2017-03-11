package model.discountstrategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Lot;
import model.MyCustomDateImpl;

/**
 * Discount strategy that suggests to discount items that are expiring within a given number of days.
 */
public class ExpiresWithinNDays implements DiscountStrategy {

    private int numberOfDays;
    private int discountAmount;

    /**
     * Basic constructor for ExpiresWithinNDays.
     * @param idiscountAmount in percentage
     * @param inumberOfDays every day of the week counts
     */
    public ExpiresWithinNDays(final int inumberOfDays, final int idiscountAmount) {
        this.numberOfDays = inumberOfDays;
        this.discountAmount = idiscountAmount;
    }

    @Override
    public Map<Lot, Integer> suggestDiscounts(final List<Lot> lots) {
        Map<Lot, Integer> result = new HashMap<>();
        List<Lot> qualifiedLots;

        qualifiedLots = lots.stream().filter(l -> l.getExpirationDate().isPresent())
                        .filter(l -> !l.isOnSale())
                        .filter(l -> 
                            (l.getExpirationDate().get().getDifferenceInDays(MyCustomDateImpl.today())
                            <=
                            (numberOfDays))
                        )
                        .collect(Collectors.toList());
        qualifiedLots.forEach(l -> result.put(l, discountAmount));

        return result;
    }

}
