package model.discountstrategies;

/**
 * Basic implementation of DiscountStrategyFactory, with all the discount strategies of the model.
 */
public class DiscountStrategyFactoryImpl implements DiscountStrategyFactory {

    @Override
    public DiscountStrategy overFiftyDiscount() {
        return new OverFiftyDiscount();
    }

}
