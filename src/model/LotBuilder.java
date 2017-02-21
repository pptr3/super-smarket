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
     * @param name Name and brand
     * @return LotBuilder for builder pattern
     */
    public LotBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Initializes the expiration date of the product.
     * @param expirationDate date in which the product will expire
     * @return LotBuilder for builder pattern
     */
    public LotBuilder expirationDate(MyCustomDate expirationDate) {
        this.expirationDate = Optional.of(expirationDate);
        return this;
    }

    /**
     * Initializes the check in date of the product.
     * @param checkInDate date in which the product was put into the warehouse
     * @return LotBuilder for builder pattern
     */
    public LotBuilder checkInDate(MyCustomDate checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    /**
     * Initializes the number of items of the lot.
     * @param quantity number of items
     * @return LotBuilder for builder pattern
     */
    public LotBuilder quantity(int quantity) {
        this.initialQuantity = quantity;
        return this;
    }

    /**
     * Initializes the price for each item of the lot.
     * @param pricePerSingleItem in cents of euro
     * @return LotBuilder for builder pattern
     */
    public LotBuilder pricePerSingleItem(int pricePerSingleItem) {
        this.pricePerSingleItem = pricePerSingleItem;
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
     * @param nextId the value of nextId
     */
    protected static void setNextId(int nextId) {
        LotBuilder.nextId = nextId;
    }

}
