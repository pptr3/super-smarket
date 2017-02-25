package controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import java.util.Map;
import java.util.Optional;

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
     * If the file indicated by filepath exist, pass an Optional of ObjectInputStream, else pass an Optional.empty.
     * @throws IOException 
     */
    @Override
    public void initialize(String filepath) throws IOException {
        this.model.initialize(Optional.of( new ObjectInputStream( new BufferedInputStream( new FileInputStream(filepath)))));
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
