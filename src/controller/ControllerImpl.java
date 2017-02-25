package controller;

import java.util.List;

import java.util.Map;

import model.Lot;
import model.Model;
import model.Warehouse;


public class ControllerImpl implements Controller {

    private Model model;
    private MyFakeView fakeView;

    public ControllerImpl() {
        this.model = new Warehouse();
        this.fakeView = new ViewImpl();
    }


    /**
     * If the file indicated by filepath exist, i pass an Optional of ObjectInputStream, else pass an Optional.empty.
     */
    @Override
    public void initialize(String filepath) {
        // TODO Auto-generated method stub
    }

    /**
     * @param filepath
     *            ObjectOutputStream
     */
    @Override
    public void saveFile(String filepath) {
        // TODO Auto-generated method stub

    }

    /**
     * @return the List of Lot
     */

    @Override
    public List<Lot> getList() {
        return this.model.getList(null);
    }

    /**
     * @param lotto adds lotto
     */
    
    @Override
    public void addLotto(Lot lotto) {
        this.model.addLotto(lotto);
    }

    /**
     * Removes 3 products from the lot with the specified id
     * @param id
     * @param n
     */
    
    @Override
    public void removeFromLotto(int id, int n) {
        this.model.removeFromLot(id, n);
    }

    @Override
    public Map<Lot, Integer> getDiscountable(String s) {
        return null;
    }

    @Override
    public void setOnSale(int id, int discountAmount) {
        this.model.setOnSale(id, discountAmount);
    }

    @Override
    public void startScan() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopScan() {
        // TODO Auto-generated method stub

    }

}
