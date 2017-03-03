package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Base menu.
 *
 */

public class BaseMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    /**
     * 
     * @param frame frame
     */

    public BaseMenu(final View frame) {
        super("Get Lots");
        JMenuItem menuItem = new JMenuItem("Choose by..");
        menuItem.addActionListener(e -> {
               //action listener per get lots
        });
        this.add(menuItem);
    }

}
