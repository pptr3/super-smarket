package controller;

import java.util.List;
import java.util.Map;

import model.Lot;
import model.Model;
import model.Pair;
import model.Warehouse;


public class ControllerImpl implements Controller {

    private Model model;
    private MyFakeView fakeView;

    public ControllerImpl() {
        this.model = new Warehouse();
        
    }

    @Override
    public void initialize(String filepath) {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveFile(String filepath) {
        // TODO Auto-generated method stub

    }
/* aspettare ad implementarlo */
    @Override
    public List<Lot> getList() {
       return this.model.getList(null);
    }

    @Override
    public void addLotto(Lot lotto) {
        this.model.addLotto(lotto);
    }

    @Override
    public void removeFromLotto(int id, int n) {
        this.model.removeFromLot(id, n);
    }
/*aspettare ad implementarlo*/
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
