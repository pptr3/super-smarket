package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse implements Model {

    private List<LotWithActions> lots;
    
    public Warehouse() {
        this.lots = new ArrayList<>();
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
        List<Lot> toReturn = new ArrayList<Lot>();
        this.lots.forEach(l -> toReturn.add(l.getLot()));
        return toReturn;
    }

    @Override
    public void addLotto(Lot lot) {
        this.lots.add(new LotImpl(lot));
    }

    @Override
    public void removeFromLot(int ID, int n) {
        this.lots.forEach(l -> {
            if (l.getId() == ID) {
                l.removeElements(n);
            }
        });
        this.lots = this.lots.stream().filter(l -> l.getCurrentQuantity() > 0).collect(Collectors.toList());

    }

    @Override
    public Map<Lot, Integer> getDiscountable(DiscountStrategy ds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOnSale(int id, int discountAmount) {
        this.lots.forEach(l -> {
            if (l.getId() == id) {
                l.setOnSale(discountAmount);
            }
        });
    }

}
