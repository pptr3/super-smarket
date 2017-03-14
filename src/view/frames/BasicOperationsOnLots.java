package view.frames;

import javax.swing.JTextArea;

import controller.Controller;
import model.Lot;

public class BasicOperationsOnLots {

    public BasicOperationsOnLots() {
        // TODO Auto-generated constructor stub
    }

    public void setLotOnSale(final Controller controller, final Integer id, final Integer quantity) {
        controller.setOnSale(id, quantity);
    }
    
    public void removeFromSale(final Controller controller, final Integer id) {
        controller.removeFromSale(id);
    }
    
    public void removeFromLot(final Controller controller, final Integer id, final Integer quantity) {
        controller.removeFromLotto(id, quantity);
    }
    
    public void updateText(final JTextArea area, final String text) {
        area.setText(text);
    }
    
    public void dontSuggest(final Controller controller, final Lot lot) {
        controller.dontSuggestAnymore(lot);
    }
}
