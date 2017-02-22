package model;

/**
 * Factory that instantiates DiscountStrategy objects.
 */
public interface DiscountStrategyFactory {

    /**
     * Returns a strategy that suggests to discount lots that haven't sold
     * at least half of their total quantity in half of their lifetime.
     * @return An OverFiftyDiscount object
     */
    DiscountStrategy overFiftyDiscount();

}
