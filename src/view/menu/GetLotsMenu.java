package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;
import model.modifylists.ModifyListFactory;
import model.modifylists.ModifyListFactoryImpl;
import view.View;

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
    private String allLots= "";
/*
 * TODO
 * not yet implemented the Text Area where show the list of lots
 */
    public GetLotsMenu(final View view, final Controller controller) {
        super("Get Lots");
        JMenuItem menuItem = new JMenuItem("Alfabetically sorted");
        menuItem.addActionListener(e -> {
            controller.getList(new ModifyListFactoryImpl().alphabeticalSorting()).forEach(l -> allLots += l.getDescription());
            view.setTextInArea(allLots);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Only expiring");
        menuItem.addActionListener(e -> {
            controller.getList(new ModifyListFactoryImpl().onlyExpiring()).forEach(l -> allLots += l.getDescription());
            view.setTextInArea(allLots);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("All");
        /*
         * with null param, i will get all the list of lots order by insertion 
         */
        menuItem.addActionListener(e -> {
            System.out.println(controller.getList(null));
        });
        this.add(menuItem);
    }

}
