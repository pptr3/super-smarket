package view.menu;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
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
    private final JFileChooser fileChooser = new JFileChooser();
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
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveFile(this.fileChooser.getSelectedFile().getPath());
                } catch (IOException e1) {
                    System.out.println("errorg");
                }
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem("Load");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                controller.initialize((this.fileChooser.getSelectedFile().getPath()));
                view.setTextInArea(String.valueOf(controller.getList(null)));
                } catch (Exception e1) {
                }
            }
        });
        this.add(menuItem);
    }
}