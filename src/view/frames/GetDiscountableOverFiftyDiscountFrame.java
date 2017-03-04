package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.Controller;
import model.Lot;

import model.discountstrategies.DiscountStrategyFactoryImpl;
import view.View;


/*
 * TODO NOTE: this class and GetDiscountableWithinAWeekFrame are almost the same, refactor
 */

/**
 * 
 *
 */
public class GetDiscountableOverFiftyDiscountFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 5926371999570025570L;
/**
 * 
 * @param controller controller
 */
    public GetDiscountableOverFiftyDiscountFrame(final View view, final Controller controller) {
        @SuppressWarnings("unused")
        //to remove when i'll use map
        Map<Lot, Integer> map = controller.getDiscountable(new DiscountStrategyFactoryImpl().overFiftyDiscount());
        JPanel panel = new JPanel(new FlowLayout());
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //TODO, do the object structure where put the information

        ActionListener al = e -> {
            new SetOnSaleFrame(view, controller);
        };

        JButton jb = new JButton("Ok set on sale");
        panel.add(jb, BorderLayout.SOUTH);
        jb.addActionListener(al);
        this.getContentPane().add(panel);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);

    }
}
