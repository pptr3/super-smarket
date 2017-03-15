package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import view.ResourceBound;
import view.frames.OperationsFramesFactoryImpl;
/**
 * 
 *
 */
public class GetDiscountableMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    private final ResourceBound res = new ResourceBound();
    /**
     * 
     * @param controller
     *            controller
     */

    public GetDiscountableMenu(final Controller controller) {
        super("Get Discountable Lots");
        JMenuItem menuItem = new JMenuItem(this.res.setName("OVER_FIFTY_DISCOUNT"));
        menuItem.addActionListener(e -> {
            new OperationsFramesFactoryImpl().getDiscountableListOfLots(controller,
                    new DiscountStrategyFactoryImpl().overFiftyDiscount());
        });
        this.add(menuItem);
        menuItem = new JMenuItem(this.res.setName("EXPIRES_WITHIN_A_WEEK"));
        menuItem.addActionListener(e -> {
            new OperationsFramesFactoryImpl().getDiscountableListOfLots(controller,
                    new DiscountStrategyFactoryImpl().expiresWithinAWeek());
        });
        this.add(menuItem);
    }

}
