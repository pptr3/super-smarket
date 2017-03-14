package view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import controller.enums.GetDiscountableNames;
import controller.enums.TitlesNames;
import model.discountstrategies.DiscountStrategyFactoryImpl;
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
    /**
     * 
     * @param controller
     *            controller
     */

    public GetDiscountableMenu(final Controller controller) {
        super(TitlesNames.GET_DISCOUNTABLE_TITLE.getName());
        JMenuItem menuItem = new JMenuItem(GetDiscountableNames.OVER_FIFTY_DISCOUNT.getName());
        menuItem.addActionListener(e -> {
            new OperationsFramesFactoryImpl().getDiscountableListOfLots(controller,
                    new DiscountStrategyFactoryImpl().overFiftyDiscount());
        });
        this.add(menuItem);
        menuItem = new JMenuItem(GetDiscountableNames.EXPIRES_WITHIN_A_WEEK.getName());
        menuItem.addActionListener(e -> {
            new OperationsFramesFactoryImpl().getDiscountableListOfLots(controller,
                    new DiscountStrategyFactoryImpl().expiresWithinAWeek());
        });
        this.add(menuItem);
    }

}
