package model;

/**
 * To be used inside the model to act on the lot, modifying its state. 
 *
 */
public interface LotWithActions extends Lot {

    /**
     * Sets the lot on sale by the specified amount.
     * @param amount in percentage
     */
    void setOnSale(int amount);

    /**
     * After this call the lot will not be on sale.
     */
    void removeFromSale();

    /**
     * Removes n elements. If it has 0 items the modelimpl should take care of that.
     * @param n number of items to remove
     */
    void removeElements(int n);


    /**
     * This method should be call after loading the model from file. It's used because Optional is not serializable. 
     */
     void initializeExpirationDateAfterDeserialization();

    /**
     * To get this object, but only with the methods of the Lot interface.
     * @return this, but only with read methods
     */
    default Lot getLot() {
        return this;
    }
}
