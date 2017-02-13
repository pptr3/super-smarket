package model;

import java.util.Optional;

/**
 * Implementation of the LotWithActions interface. This should be used inside the Model implementation
 * but not in the view, since it also has methods for modifying the content of the lot. 
 * In the view LotImpl objects should be cast to Lot
 */
public class LotImpl implements LotWithActions {

    private int id;
    private String name;
    private Optional<MyCustomDate> expirationDate;
    private MyCustomDate checkInDate;
    private int initialQuantity;
    private int currentQuantity;
    private int pricePerSingleItem;
    private boolean onSale;
    private int salePercentage;

    /**
     * Basic constructor with all the needed parameters.
     * @param id given by the builder
     * @param name name of the product and brand
     * @param checkInDate when it's been delivered to the warehouse
     * @param expirationDate optional, not all items have expiration date
     * @param quantity how many items were in the lot when it arrived in the warehouse
     * @param pricePerSingleItem in euro cents
     */
    public LotImpl(final int id, final String name, final MyCustomDate checkInDate, 
            final Optional<MyCustomDate> expirationDate, 
            final int quantity, final int pricePerSingleItem) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.checkInDate = checkInDate;
        this.initialQuantity = quantity;
        this.currentQuantity = quantity;
        this.pricePerSingleItem = pricePerSingleItem;
        this.onSale = false;
        this.salePercentage = 0;
    }

    /**
     * Creates a LotImpl starting with the information inside a Lot.
     * @param lot initial information
     */
    public LotImpl(final Lot lot) {
        this(lot.getId(), lot.getName(), lot.getCheckInDate(), 
                lot.getExpirationDate(), lot.getCurrentQuantity(), lot.getPricePerSingleItem());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<MyCustomDate> getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public MyCustomDate getCheckInDate() {
        return this.checkInDate;
    }

    @Override
    public int getPricePerSingleItem() {
        return this.pricePerSingleItem;
    }

    @Override
    public boolean isOnSale() {
        return this.onSale;
    }

    @Override
    public int getCurrentQuantity() {
        return this.currentQuantity;
    }

    @Override
    public int getInitialQuantity() {
        return this.initialQuantity;
    }

    @Override
    public void removeElements(final int n) {
        this.currentQuantity -= n;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setOnSale(final int amount) {
        this.onSale = true;
        this.salePercentage = amount;
    }

    @Override
    public void removeFromSale() {
        this.onSale = false;
        this.salePercentage = 0;
    }

    @Override
    public int getSalePercentage() {
        return this.salePercentage;
    }

    @Override
    public String toString() {
        return " Id=" + id + "\n" + "Name=" + name + "\n" + "ExpirationDate=" + expirationDate + "\n" + "CheckInDate="
                + checkInDate + "\n" + " InitialQuantity=" + initialQuantity + "\n" + "CurrentQuantity=" + currentQuantity
                + "\n" + "PricePerSingleItem=" + pricePerSingleItem + "\n" + "OnSale=" + onSale + "\n" + "SalePercentage="
                + salePercentage + "\n\n";
    }

}
