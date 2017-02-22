package model.discountstrategies;

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

    /**
     * Returns a strategy that suggests to discount lots that have less 
     * than one week of lifetime.
     * @return an ExpiresWithinNDays object
     */
    DiscountStrategy expiresWithinAWeek();

    /**
     * Returns a strategy that suggests to discount lots that are
     * expiring within one day.
     * @return an ExpiresWithinNDays object
     */
    DiscountStrategy expiresWithinOneDay();

}
