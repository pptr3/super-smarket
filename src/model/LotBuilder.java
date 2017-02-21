package model;

import java.util.Optional;

/**
 * Only access point for creating lots.
 */
public class LotBuilder {


    private static int nextId = 0;

    private int id;
    private String name;
    private Optional<MyCustomDate> expirationDate;
    private MyCustomDate checkInDate;
    private int initialQuantity;
    private int pricePerSingleItem;

    /**
     * Default constructor, initializes expiration date to empty.
     */
    public LotBuilder() {
        this.expirationDate = Optional.empty();
        //this.checkInDate = today;
        //TODO: find a way to set the checkInDate to today's date by default
    }


    /**
     * Initializes the name of the product.
     * @param pname Name and brand
     * @return LotBuilder for builder pattern
     */
    public LotBuilder name(final String pname) {
        this.name = pname;
        return this;
    }

    /**
     * Initializes the expiration date of the product.
     * @param pexpirationDate date in which the product will expire
     * @return LotBuilder for builder pattern
     */
    public LotBuilder expirationDate(final MyCustomDate pexpirationDate) {
        this.expirationDate = Optional.of(pexpirationDate);
        return this;
    }

    /**
     * Initializes the check in date of the product.
     * @param pcheckInDate date in which the product was put into the warehouse
     * @return LotBuilder for builder pattern
     */
    public LotBuilder checkInDate(final MyCustomDate pcheckInDate) {
        this.checkInDate = pcheckInDate;
        return this;
    }

    /**
     * Initializes the number of items of the lot.
     * @param pquantity number of items
     * @return LotBuilder for builder pattern
     */
    public LotBuilder quantity(final int pquantity) {
        this.initialQuantity = pquantity;
        return this;
    }

    /**
     * Initializes the price for each item of the lot.
     * @param ppricePerSingleItem in cents of euro
     * @return LotBuilder for builder pattern
     */
    public LotBuilder pricePerSingleItem(final int ppricePerSingleItem) {
        this.pricePerSingleItem = ppricePerSingleItem;
        return this;
    }


    /**
     * Actually builds the lot and assigns the id.
     * @return the lot whose information were stored inside LotBuilder
     */
    public Lot build() {
        this.id = getNextId();
        setNextId(getNextId() + 1);
        return new LotImpl(this.id, this.name, this.checkInDate, this.expirationDate, this.initialQuantity, this.pricePerSingleItem);
    }

    /**
     * getter for nextId.
     * @return the value of nextId
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * setter for nextId.
     * @param newNextId the value of nextId
     */
    protected static void setNextId(final int newNextId) {
        LotBuilder.nextId = newNextId;
    }

}
