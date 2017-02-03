package model;

import java.util.Date;

public class LotImpl implements Lot {

    private String name;
    private Date expirationDate;
    private Date checkInDate;
    private int initialQuantity;
    private int currentQuantity;
    private int pricePerSingleItem;
    private boolean onSale;
    
    
    
    public LotImpl(String name, Date checkInDate, Date expirationDate, int quantity,
            int pricePerSingleItem) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.checkInDate = checkInDate;
        this.initialQuantity = quantity;
        this.currentQuantity = quantity;
        this.pricePerSingleItem = pricePerSingleItem;
        onSale = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public Date getCheckInDate() {
        return checkInDate;
    }

    @Override
    public int getPricePerSingleItem() {
        return pricePerSingleItem;
    }

    @Override
    public boolean isOnSale() {
        return onSale;
    }

    @Override
    public int getCurrentQuantity() {
        return currentQuantity;
    }

    @Override
    public int getInitialQuantity() {
        return initialQuantity;
    }

    @Override
    public void removeElements(int n) {
        currentQuantity -= n;
    }

}
