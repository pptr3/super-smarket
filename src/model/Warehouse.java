package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Warehouse implements Model{

    List<Lot> lots;
    
    public Warehouse() {
        lots = new ArrayList<>();
    }
    
    @Override
    public void initialize(String serializedModel) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String serializeModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Lot> getList(ModifyList mfl) {
        return lots;
    }

    @Override
    public void addLotto(Lot lot) {
        lots.add(lot);
    }

    @Override
    public void removeFromLot(int ID, int n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Map<Lot, Integer> getDiscountable(DiscountStrategy ds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOnSale(int id, int discountAmount) {
        // TODO Auto-generated method stub
        
    }

}