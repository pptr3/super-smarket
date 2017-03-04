package view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;

import controller.Controller;
import controller.ControllerImpl;
import model.Lot;
import model.Warehouse;
import model.discountstrategies.DiscountStrategyFactoryImpl;

public class GetDiscountableOverFiftyDiscountFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 5926371999570025570L;
    private Controller controller;
    
    public GetDiscountableOverFiftyDiscountFrame() {
        this.controller = new ControllerImpl(new Warehouse());
        Map<Lot, Integer> map = this.controller.getDiscountable(new DiscountStrategyFactoryImpl().overFiftyDiscount());
        // TODO the interface that show you the Lots that need to be discounted due the strategy of this class(over fifty
        //discount)
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);

    }
}
