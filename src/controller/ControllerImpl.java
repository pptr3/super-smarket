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
        this.fakeView = new ViewImpl();
    }

    @Override
    public void initialize(String filepath) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveFile(String filepath) {
        // TODO Auto-generated method stub

    }
/* ambigua signature del metodo.. mettersi d'accordo prima di implementarlo*/
    @Override
    public List<Lot> getList() {
       return null;
    }

    @Override
    public void addLotto(Lot lotto) {
        this.model.addLotto(lotto);
    }

    @Override
    public void removeFromLotto(int ID, int n) {
        this.model.removeFromLot(ID, n);
    }
/*ambiguità signature..wait*/
    @Override
    public Map<Lot, Integer> getDiscountable(String s) {
        return null;
    }

    @Override
    public void setOnSale(List<Pair<Integer, Integer>> list) {
        // TODO Auto-generated method stub

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
