package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse implements Model{

    Map<Lot,Integer> lotsAndDiscounts;
    
    public Warehouse() {
        lotsAndDiscounts = new HashMap<>();
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
        return lotsAndDiscounts.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public void addLotto(Lot lot) {
        lotsAndDiscounts.put(lot, 0);
    }

    @Override
    public void removeFromLot(int ID, int n) {
        Lot target = null;
        for (Lot l : lotsAndDiscounts.keySet()) {
            if (l.getId() == ID) {
                target = l;
            }
        }
        if (target!=null) {
            target.removeElements(n);
            if (target.getCurrentQuantity() == 0 ) {
                lotsAndDiscounts.remove(target);
            }
        }
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
