package model;

import java.util.Date;
import java.util.Optional;

public class LotBuilder {

    private static int nextId = 0;
    
    private int id;
    private String name;
    private Optional<Date> expirationDate;
    private Date checkInDate;
    private int initialQuantity;
    private int currentQuantity;
    private int pricePerSingleItem;
    private boolean onSale;
    
    public LotBuilder() {
        this.id = nextId++;
        this.expirationDate = Optional.empty();
        //this.checkInDate = today;
        //TODO: find a way to set the checkInDate to today's date by default
        this.onSale = false;
    }
    
    public LotBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public LotBuilder expirationDate(Date expirationDate) {
        this.expirationDate = Optional.of(expirationDate);
        return this;
    }
    
    public LotBuilder checkInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }
    
    public LotBuilder quantity(int quantity) {
        this.initialQuantity = quantity;
        this.currentQuantity = quantity;
        return this;
    }
    
    public LotBuilder pricePerSingleItem(int pricePerSingleItem) {
        this.pricePerSingleItem = pricePerSingleItem;
        return this;
    }
    
    public LotBuilder onSale(boolean onSale) {
        this.onSale = onSale;
        return this;
    }
    
    public Lot build() {
        return new LotImpl(this.id, this.name, this.checkInDate, this.expirationDate.get(), this.initialQuantity, this.pricePerSingleItem);
    }
    
}
