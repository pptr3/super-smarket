package model;

import java.util.Date;
import java.util.Optional;

public class LotImpl implements Lot {

    private int id;
    private String name;
    private Optional<Date> expirationDate;
    private Date checkInDate;
    private int initialQuantity;
    private int currentQuantity;
    private int pricePerSingleItem;
    private boolean onSale;
    private int salePercentage;
    
    
    
    public LotImpl(int id, String name, Date checkInDate, Optional<Date> expirationDate, int quantity,
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<Date> getExpirationDate() {
        return expirationDate;
    }

    @Override
    public Date getCheckInDate() {
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
        return id;
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

}
