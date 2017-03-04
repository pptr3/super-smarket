package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
     * @param frame frame
     */

    public GetLotsMenu(final View frame) {
        super("Get Lots");
        JMenuItem menuItem = new JMenuItem("Alfabetically sorted");
        menuItem.addActionListener(e -> {
               //action listener per get lots
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Only expiring");
        menuItem.addActionListener(e -> {
           //action listener...
        });
        this.add(menuItem);
    }

}
