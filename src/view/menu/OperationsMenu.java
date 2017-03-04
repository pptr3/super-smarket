package view.menu;


import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import view.View;
import view.frames.AddLotsFrame;
import view.frames.RemoveLotsFrame;
import view.frames.SetOnSaleFrame;

/**
 *
 */
public class OperationsMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;

    /**
     * @param controller controller
     * @param view view
     */

    public OperationsMenu(final View view, final Controller controller) {
        super("Operations");
        JMenuItem menuItem = new JMenuItem("Add Lot");
        menuItem.addActionListener(e -> {
               new AddLotsFrame(view, controller);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Remove Lot");
        menuItem.addActionListener(e -> {
            new RemoveLotsFrame(view, controller);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Set Lot on sale");
        menuItem.addActionListener(e -> {
               new SetOnSaleFrame(view, controller);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
               //TODO 
        });
        this.add(menuItem);

        menuItem = new JMenuItem("Reset");
        menuItem.addActionListener(e -> {
               //TODO 
        });
        this.add(menuItem);
    }
}