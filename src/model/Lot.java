package model;

import java.util.Optional;

/**
 * Represents a Lot, with the methods for getting the value of all of its fields.
 */
public interface Lot {

    /**
     * @return the id of the lot
     */
    int getId(); 

    /**
     * @return name of the product
     */
    String getName();

    /**
     * @return expirationDate, which could also not be present
     */
    Optional<MyCustomDate> getExpirationDate();

    /**
     * @return date in which the Lot was delivered to the warehouse
     */
    MyCustomDate getCheckInDate();

    /**
     * @return number of items initially stored in the lot
     */
    int getInitialQuantity();

    /**
     * @return number of items currently stored in the lot
     */
    int getCurrentQuantity();

    /**
     * Price is in cents of euro.
     * @return cents of euro
     */
    int getPricePerSingleItem();

    /**
     * @return true if the lot is on sale, false otherwise
     */
    boolean isOnSale();

    /**
     * @return 0 (not on sale) - 100 (free)
     */
    int getSalePercentage(); 
}
