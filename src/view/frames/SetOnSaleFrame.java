package view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import view.View;

/**
 * Set on sale some lots.
 *
 */
public class SetOnSaleFrame extends AbstractCustomFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4833819912763016964L;
    private final List<JTextField> sale = new ArrayList<>();
    private String allLots = "";
    /**
     * 
     * @param controller
     *            controller
     * @param view view
     */
    public SetOnSaleFrame(final View view, final Controller controller) {
        this.setLayout(new FlowLayout());
        this.setTitle("Set Lot on sale");
        final JPanel center = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                center.add(wrapperPanel(new JLabel("ID:"), FlowLayout.LEFT));
            } else {
                center.add(wrapperPanel(new JLabel("% Sale:"), FlowLayout.LEFT));
            }
            final JTextField jText = new JTextField(3);
            this.sale.add(jText);
            center.add(wrapperPanel(jText, FlowLayout.CENTER));
        }

        final JButton sales = new JButton("Confirm Sale");

        final JButton back = new JButton("Back");
        final JPanel south = new JPanel();
        south.add(back, BorderLayout.SOUTH);
        south.add(sales, BorderLayout.SOUTH);

        final ActionListener alRemove = e3 -> {
            controller.setOnSale(Integer.parseInt(this.sale.get(0).getText()),
                    Integer.parseInt(this.sale.get(1).getText()));
            controller.getList(null).forEach(l2 -> allLots += l2.getDescription());
            view.setTextInArea(allLots);
            this.sale.clear();
            this.setVisible(false);
        };

        final ActionListener al4 = e2 -> {
            this.setVisible(false);
        };
        sales.addActionListener(alRemove);
        back.addActionListener(al4);
        this.getContentPane().add(center);
        this.getContentPane().add(south);
        this.initializeSizeAndLocation();
        this.pack();

    }
}
