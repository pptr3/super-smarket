package view.menu;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.frames.AddLotsFrame;
import view.frames.GetLotsFrame;

/**
 *
 */
public class AddLotsMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;

    /**
     * 
     * @param frame
     */

    public AddLotsMenu(final JFrame frame) {
        super("Operation");
        JMenuItem menuItem = new JMenuItem("Add");
        menuItem.addActionListener(e -> {
               new AddLotsFrame();
        });
        this.add(menuItem);
        
        menuItem = new JMenuItem("Remove");
        menuItem.addActionListener(e -> {
            new RemoveLotsFrame();
        });
        this.add(menuItem);
        
        menuItem = new JMenuItem("Set On sale");
        menuItem.addActionListener(e -> {
               new SetOnSaleFrame();
        });
        this.add(menuItem);
        
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
               //TODO 
        });
        this.add(menuItem);
    }

}