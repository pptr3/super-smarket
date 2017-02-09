package model;

public interface LotWithActions extends Lot {

    void setOnSale(int amount);
    
    void removeFromSale();
    
    void removeElements(int n);
    
    default Lot getLot() {
        return this;
    }
}
