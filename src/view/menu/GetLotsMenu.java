package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import model.modifylists.ModifyList;
import model.modifylists.ModifyListFactoryImpl;
import view.View;
import view.frames.DiscountableFrames;

/**
 * Base menu.
 *
 */

public class GetLotsMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    /**
     * 
     * @param view where show the list of lots
     */

    /**
     * String to be used to get the description of all the lots.
     */
    private String allLots = "";
/**
 * 
 * @param view view
 * @param controller controller
 */
    public GetLotsMenu(final View view, final Controller controller) {
        super("Get Lots");
        JMenuItem menuItem = new JMenuItem("Alfabetically sorted");
        menuItem.addActionListener(e -> {
            setTextArea(controller, new ModifyListFactoryImpl().alphabeticalSorting());
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Only expiring");
        menuItem.addActionListener(e -> {
            setTextArea(controller, new ModifyListFactoryImpl().onlyExpiring());
        });
        this.add(menuItem);
        menuItem = new JMenuItem("All");
        /*
         * with null  @param, @return the list of lots order by insertion 
         */
        menuItem.addActionListener(e -> {
            setTextArea(controller, null);
        });
        this.add(menuItem);
    }

    private void setTextArea(final Controller controller, final ModifyList ml) {
        new DiscountableFrames(controller, controller.getList(ml));
    }
}
