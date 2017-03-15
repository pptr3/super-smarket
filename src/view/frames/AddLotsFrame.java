package view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import model.Lot;
import model.LotBuilder;
import model.MyCustomDate;
import model.MyCustomDateImpl;
import view.ResourceBound;

/**
 *
 */
public class AddLotsFrame extends CustomFrame {

    private static final long serialVersionUID = 6784928917074846594L;
    private static final Integer START = 5;
    private static final Integer END = 7;
    private final ResourceBound res = new ResourceBound();
    private final List<String> list = new ArrayList<>(Arrays.asList(this.res.setName("NAME"),
            this.res.setName("CHECK_IN_DATE"), this.res.setName("EXPIRATION_DATE"),
            this.res.setName("INITIAL_QUANTITY"), this.res.setName("PRICE_PER_SINGLE_ITEM")));
    private final List<JTextField> jtext = new ArrayList<>();

    /**
     * @param controller
     *            controller
     */
    public AddLotsFrame(final Controller controller) {
        final JPanel center = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < this.list.size(); i++) {
            center.add(wrapperPanel(new JLabel(this.list.get(i)), FlowLayout.LEFT));
            final JTextField jText = new JTextField(20);
            this.jtext.add(jText);
            center.add(wrapperPanel(jText, FlowLayout.CENTER));
        }
        this.setTitle(this.res.setName("ADD_LOT"));
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        final JButton turnBack = new JButton(this.res.setName("BACK"));
        panel.add(turnBack);

        final JButton confirm = new JButton(this.res.setName("CONFIRM"));
        panel.add(confirm);

        final ActionListener al = e -> {
            try {
                Lot l = new LotBuilder().name(this.jtext.get(0).getText())
                        .checkInDate(initializeDate(this.jtext, 1))
                        .expirationDate(initializeDate(this.jtext, 2))
                        .quantity(Integer.parseInt(this.jtext.get(3).getText()))
                        .pricePerSingleItem(Integer.parseInt(this.jtext.get(4).getText()))
                        .build();
                this.jtext.clear();
                controller.addLotto(l);
                this.setVisible(false);
            } catch (IllegalStateException e2) {
                controller.getSubject().showMessageErrorView(e2.getMessage());
            } catch (Exception e3) {
                controller.getSubject().showMessageErrorView(this.res.setName("FORMAT"));
            }
        };
        final ActionListener al2 = e -> {
            this.setVisible(false);
        };

        confirm.addActionListener(al);
        turnBack.addActionListener(al2);
        this.getContentPane().add(center, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.SOUTH);
        this.initializeSizeAndLocation();
        this.pack();
    }
/**
 * 
 * @param text tet to set
 * @param index index
 * @return new MyCustimDate object
 */
    private MyCustomDate initializeDate(final List<JTextField> text, final Integer index) {
        return new MyCustomDateImpl(Integer.parseInt(text.get(index).getText().substring(0, 4)),
                Integer.parseInt(text.get(index).getText().substring(START, END)),
                Integer.parseInt(text.get(index).getText().substring(8, 10)));
    }
    /**
     * Create a new panel containing a new component with the given orientation.
     * @param component component
     * @param orientation orientation
     * @return new JPanel
     */
    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }

}
