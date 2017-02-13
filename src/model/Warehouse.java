package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.attribute.AclEntry.Builder;
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
    public void initialize(final ObjectInputStream serializedModel, final int lastId) {
        try {
            lots.add((LotWithActions) serializedModel.readObject());
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LotBuilder.nextId = lastId;

    }

    @Override
    public int serializeModel(final ObjectOutputStream output) {
        getList(null).forEach(l -> {
            try {
                output.writeObject(l);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return LotBuilder.nextId;
    }

    @Override
    public List<Lot> getList(final ModifyList mfl) {
        final List<Lot> toReturn = new ArrayList<Lot>();
        this.lots.forEach(l -> toReturn.add(l.getLot()));
        if (mfl != null) {
            return mfl.modify(toReturn);
        }
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
