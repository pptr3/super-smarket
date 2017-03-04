package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.View;
import view.frames.GetDiscountableOverFiftyDiscountFrame;
import view.frames.GetDiscountableWithinAWeekFrame;
/**
 * 
 *
 */
public class GetDiscountableMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    /**
     * 
     * @param frame frame
     */

    public GetDiscountableMenu(final View frame) {
        super("Get Discountable Lots");
        JMenuItem menuItem = new JMenuItem("Over fifty discount");
        menuItem.addActionListener(e -> {
               new GetDiscountableOverFiftyDiscountFrame();
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Expires within a week");
        menuItem.addActionListener(e -> {
           new GetDiscountableWithinAWeekFrame();
        });
        this.add(menuItem);
    }

}
