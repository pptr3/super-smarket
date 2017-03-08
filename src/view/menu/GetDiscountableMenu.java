package view.menu;

import javax.swing.JMenu;

import javax.swing.JMenuItem;
import controller.Controller;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import view.View;
import view.frames.GetDiscountableFrames;
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
    * @param controller controller
    */

    public GetDiscountableMenu(final View view, final Controller controller) {
        super("Get Discountable Lots");
        JMenuItem menuItem = new JMenuItem("Over fifty discount");
        menuItem.addActionListener(e -> {
               new GetDiscountableFrames(view, controller, new DiscountStrategyFactoryImpl().overFiftyDiscount());
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Expires within a week");
        menuItem.addActionListener(e -> {
           new GetDiscountableFrames(view, controller, new DiscountStrategyFactoryImpl().expiresWithinAWeek());
        });
        this.add(menuItem);
    }

}
