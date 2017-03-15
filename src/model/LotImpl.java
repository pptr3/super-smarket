package model;

import java.io.Serializable;
import java.util.Optional;

import view.ResourceBound;

/**
 * Implementation of the LotWithActions interface. This should be used inside the Model implementation
 * but not in the view, since it also has methods for modifying the content of the lot. 
 * In the view LotImpl objects should be cast to Lot
 */
public class LotImpl implements LotWithActions, Serializable {

    private static String emptyOptionalString = "empty";

    /**
     * 
     */
    private static final long serialVersionUID = -4379413488270752232L;
    private int id;
    private String name;
    private transient Optional<MyCustomDate> expirationDate;
    private String expirationDateSerialization;
    private MyCustomDate checkInDate;
    private int initialQuantity;
    private int currentQuantity;
    private int pricePerSingleItem;
    private boolean onSale;
    private int salePercentage;
    private final ResourceBound res;
    /**
     * Basic constructor with all the needed parameters.
     * @param iid given by the builder
     * @param iname name of the product and brand
     * @param icheckInDate when it's been delivered to the warehouse
     * @param iexpirationDate optional, not all items have expiration date
     * @param iquantity how many items were in the lot when it arrived in the warehouse
     * @param ipricePerSingleItem in euro cents
     */
    public LotImpl(final int iid, final String iname, final MyCustomDate icheckInDate, 
            final Optional<MyCustomDate> iexpirationDate, 
            final int iquantity, final int ipricePerSingleItem) {
        this.id = iid;
        this.name = iname;
        this.expirationDate = iexpirationDate;
        this.checkInDate = icheckInDate;
        this.initialQuantity = iquantity;
        this.currentQuantity = iquantity;
        this.pricePerSingleItem = ipricePerSingleItem;
        this.onSale = false;
        this.salePercentage = 0;
        this.res = new ResourceBound();

        if (iexpirationDate.isPresent()) {
            this.expirationDateSerialization = this.expirationDate.get().getDateToString();
        } else {
            this.expirationDateSerialization = emptyOptionalString;
        }
    }

    /**
     * Creates a LotImpl starting with the information inside a Lot.
     * @param lot initial information
     */
    public LotImpl(final Lot lot) {
        this(lot.getId(), lot.getName(), lot.getCheckInDate(), 
                lot.getExpirationDate(), lot.getCurrentQuantity(), lot.getPricePerSingleItem());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<MyCustomDate> getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public MyCustomDate getCheckInDate() {
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
    public void removeElements(final int n) {
        if (this.currentQuantity < n) {
            throw new IllegalStateException((this.res.setName("CANT_REMOVE_ELEMENTS")));
        }
        this.currentQuantity -= n;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setOnSale(final int amount) {
        if (this.isOnSale()) {
            throw new IllegalStateException((this.res.setName("ALREADY_ON_SALE")));
        }
        this.onSale = true;
        this.salePercentage = amount;
    }

    @Override
    public void removeFromSale() {
        if (!this.isOnSale()) {
            throw new IllegalStateException((this.res.setName("NOT_ON_SALE")));
        }
        this.onSale = false;
        this.salePercentage = 0;
    }

    @Override
    public int getSalePercentage() {
        return this.salePercentage;
    }

    @Override
    public String getDescription() {
        return "Name: " + name + "\n" + "ExpirationDate: " + expirationDate.get().getDateToString() + "\n" + "CheckInDate: "
                + checkInDate.getDateToString() + "\n" + " InitialQuantity: " + initialQuantity + "\n" + "CurrentQuantity: " + currentQuantity
                + "\n" + "PricePerSingleItem: " + pricePerSingleItem + "\n" + "OnSale: " + onSale + "\n" + "SalePercentage: "
                + salePercentage + "\n\n";
    }

    /**
     * This method should be call after loading the model from file. It's used because Optional is not serializable. 
     */
    public void initializeExpirationDateAfterDeserialization() {
        if (this.expirationDateSerialization.equals(emptyOptionalString)) {
            this.expirationDate = Optional.empty();
        } else {
            final String[] date = this.expirationDateSerialization.split("-");
            this.expirationDate = Optional.of(new MyCustomDateImpl(
                    Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
        }
    }

}
