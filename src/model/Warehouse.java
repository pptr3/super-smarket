package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Model implementation representing a warehouse in which lots are stored.
 * Can also perform other actions as described by the interface
 */

public class Warehouse implements Model {

    private List<LotWithActions> lots;

    /**
     * Default constructor that initializes the internal list.
     */
    public Warehouse() {
        this.lots = new ArrayList<>();
    }

    @Override
    public void initialize(final String serializedModel) {
        // TODO Auto-generated method stub

    }

    @Override
    public String serializeModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Lot> getList(final ModifyList mfl) {
        final List<Lot> toReturn = new ArrayList<Lot>();
        this.lots.forEach(l -> toReturn.add(l.getLot()));
        return toReturn;
    }

    @Override
    public void addLotto(final Lot lot) {
        this.lots.add(new LotImpl(lot));
    }

    @Override
    public void removeFromLot(final int id, final int n) {
        this.lots.forEach(l -> {
            if (l.getId() == id) {
                l.removeElements(n);
            }
        });
        this.lots = this.lots.stream().filter(l -> l.getCurrentQuantity() > 0).collect(Collectors.toList());

    }

    @Override
    public Map<Lot, Integer> getDiscountable(final DiscountStrategy ds) {
        return ds.suggestDiscounts(this.getList(null));
    }

    @Override
    public void setOnSale(final int id, final int discountAmount) {
        this.lots.forEach(l -> {
            if (l.getId() == id) {
                l.setOnSale(discountAmount);
            }
        });
    }
}
