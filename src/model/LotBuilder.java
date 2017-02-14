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


    public LotBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LotBuilder expirationDate(MyCustomDate expirationDate) {
        this.expirationDate = Optional.of(expirationDate);
        return this;
    }

    public LotBuilder checkInDate(MyCustomDate checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public LotBuilder quantity(int quantity) {
        this.initialQuantity = quantity;
        return this;
    }

    public LotBuilder pricePerSingleItem(int pricePerSingleItem) {
        this.pricePerSingleItem = pricePerSingleItem;
        return this;
    }


    public Lot build() {
        this.id = getNextId();
        setNextId(getNextId() + 1);
        return new LotImpl(this.id, this.name, this.checkInDate, this.expirationDate, this.initialQuantity, this.pricePerSingleItem);
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        LotBuilder.nextId = nextId;
    }

}
