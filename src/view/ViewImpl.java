package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import controller.Controller;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import model.resourcebundle.ResourceBound;
import view.menu.OperationsMenu;
import view.frames.OperationsFramesFactoryImpl;
import view.menu.GetDiscountableMenu;
import view.menu.GetLotsMenu;


/**
 * Implementation of View interface.
 *
 */

public class ViewImpl extends JFrame implements View {

    /**
     * 
     */

    private static final long serialVersionUID = -2914580357647719433L;
    private final Controller controller;
    private final JMenuBar menuBar = new JMenuBar();
    private final GetLotsMenu getLots;
    private final GetDiscountableMenu getDiscountable;
    private final JMenu operations;
    //private final ScanMenu scan;
    private final ResourceBound res;

    /**
     * 
     * @param cont
     *            controller
     */
    public ViewImpl(final Controller cont) {
        this.controller = cont;
        this.res = new ResourceBound();
        this.operations = new OperationsMenu(controller);
        this.getDiscountable = new GetDiscountableMenu(this.controller);
        this.getLots = new GetLotsMenu(controller);
       // this.scan = new ScanMenu(controller);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle(this.res.setName("TITLE_APPLICATION"));
        this.menuBar.add(this.getLots);
        this.menuBar.add(this.getDiscountable);
        this.menuBar.add(this.operations);
       // this.menuBar.add(this.scan);
        controller.startScan();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setLocation((dim.width - this.getSize().width), (dim.height - this.getSize().height));
        this.setVisible(true);
    }
    /**
     * 
     */
    public void update() {
        errorMessage(this.res.setName("LOT_TO_DISCOUNT"));
        new OperationsFramesFactoryImpl().getDiscountableListOfLots(controller,
                new DiscountStrategyFactoryImpl().expiresWithinOneDay());
    }
    /**
     * @param message message to show
     */
    public void errorMessage(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
