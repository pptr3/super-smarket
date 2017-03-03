package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import controller.Controller;
import controller.ControllerImpl;
import model.Lot;
import model.Warehouse;

/**
 *Implementation of View interface.
 *
 */


public class ViewImpl extends JFrame implements View {

    /**
     * 
     */
    private static final long serialVersionUID = -2914580357647719433L;
    private final Controller controller;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu addLots = new AddLotsMenu(this);
    private final GetLotsMenu getLots = new GetLotsMenu(this);
    private final RemoveMenu remove = new RemoveMenu(this);
    private final SetOnSaleMenu setOnSale = new SetOnSaleMenu(this);
    private final ScanMenu scan = new ScanMenu(this);
    private final SaveMenu save = new SaveMenu(this);
    
    
    public ViewImpl(final Controller cont) {
        this.controller = cont;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("SuperSmarket");
        this.menuBar.add(this.getLots);
        this.menuBar.add(this.addLots);
        this.menuBar.add(this.remove);
        this.menuBar.add(this.setOnSale);
        this.menuBar.add(this.scan);
        this.menuBar.add(this.save);
        
        
        
        
        
        
        
        
        
        
        
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        new ViewImpl(new ControllerImpl(new Warehouse()));
    }
    
    
    
    
    
    
    
    
    
    @Override
    public void discountAdvice(Map<Lot, Integer> lots) {
        // TODO Auto-generated method stub
        
    }

}

