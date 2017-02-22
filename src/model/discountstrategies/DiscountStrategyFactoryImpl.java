package model.discountstrategies;

/**
 * Basic implementation of DiscountStrategyFactory, with all the discount strategies of the model.
 */
public class DiscountStrategyFactoryImpl implements DiscountStrategyFactory {

    private final int daysInAWeek = 7;
    private final int smallDiscount = 20;

    @Override
    public DiscountStrategy overFiftyDiscount() {
        return new OverFiftyDiscount();
    }

    @Override
    public DiscountStrategy expiresWithinAWeek() {
        return new ExpiresWithinNDays(daysInAWeek, smallDiscount);
    }

    @Override
    public DiscountStrategy expiresWithinOneDay() {
        return new ExpiresWithinNDays(1, smallDiscount);
    }

}
