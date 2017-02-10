package model;

import java.util.Date;
import java.util.Optional;

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
    
    public LotImpl(int id, String name, MyCustomDate checkInDate, Optional<MyCustomDate> expirationDate, int quantity,
            int pricePerSingleItem) {
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

    public LotImpl(Lot lot) {
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
    public void removeElements(int n) {
        this.currentQuantity -= n;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setOnSale(int amount) {
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
        return "LotImpl [id=" + id + ", name=" + name + "]";
    }
    
    
}
