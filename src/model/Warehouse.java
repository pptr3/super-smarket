package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse implements Model{

<<<<<<< HEAD
    Map<Lot,Integer> lotsAndDiscounts;
    
    public Warehouse() {
        lotsAndDiscounts = new HashMap<>();
=======
    private List<Lot> lots;
    
    public Warehouse() {
        this.lots = new ArrayList<>();
>>>>>>> 0fbfb963be4b44babac862d1adc793af2effd3c5
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
<<<<<<< HEAD
        return lotsAndDiscounts.keySet().stream().collect(Collectors.toList());
=======
        return this.lots;
>>>>>>> 0fbfb963be4b44babac862d1adc793af2effd3c5
    }

    @Override
    public void addLotto(Lot lot) {
<<<<<<< HEAD
        lotsAndDiscounts.put(lot, 0);
=======
        this.lots.add(lot);
>>>>>>> 0fbfb963be4b44babac862d1adc793af2effd3c5
    }

    @Override
    public void removeFromLot(int ID, int n) {
        Lot target = findLotById(ID);
        if (target!=null) {
            target.removeElements(n);
            if (target.getCurrentQuantity() == 0 ) {
                lotsAndDiscounts.remove(target);
            }
        }
    }

    private Lot findLotById(int ID) {
        Lot target = null;
        for (Lot l : lotsAndDiscounts.keySet()) {
            if (l.getId() == ID) {
                target = l;
            }
        }
        return target;
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
