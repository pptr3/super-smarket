package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.Controller;
import model.Lot;
import model.discountstrategies.DiscountStrategy;


/*
 * TODO: NOTE: implement what appens when there is no Lots
 * TODO: Manca la ScrollBar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * TODO: Un po di refactor per codice duplicato
 */

/**
 * Abstract class for Template Method.
 *
 */
public class DiscountableFrames extends AbstractCustomFrame {

    /**
     * 
     */
    private static final String SET_ON_SALE = "Set on sale";
    private static final String PERCENTAGE = "%";
    private static final String DONT_SUGGEST_ANYMORE = "Don't suggest anymore";
    private static final Integer DEFAUL_DISCOUNT = 10;
    private static final long serialVersionUID = 5926371999570025570L;
    private static final Integer COLS = 2;
    private static final String REMOVE_FROM_SALE = "Remove from sale";
    private static final String REMOVE_LOT = "Remove";
    private static final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
    private final List<JTextField> texts = new ArrayList<>();
    private final List<JTextField> textForRemotion = new ArrayList<>();
    private final List<JButton> discard = new ArrayList<>();
    private final List<JButton> setOnSale = new ArrayList<>();
    private final List<JButton> removeLot = new ArrayList<>();
    private final List<JButton> removeFromSale = new ArrayList<>();
    private final List<Lot> lots = new ArrayList<>();
    private final List<JTextArea> area = new ArrayList<>();

    /**
     * 
     * @param controller
     *            controller
     * @param ds
     *            discount strategy
     */
    public DiscountableFrames(final Controller controller, final DiscountStrategy ds) {
        final Map<Lot, Integer> map = controller.getDiscountable(ds);
        final JPanel center = new JPanel(new GridLayout(map.size(), COLS));

        final ActionListener al3 = e -> {
            JButton jb = (JButton) e.getSource();
            controller.dontSuggestAnymore(this.lots.get(this.discard.indexOf(jb)));
        };

        for (final Map.Entry<Lot, Integer> lot : map.entrySet()) {
            final JPanel buttons = new JPanel(new FlowLayout());
            this.initializaFrame(buttons, center, this.area, this.lots, this.setOnSale, this.texts, this.removeFromSale, lot.getKey(), lot.getValue());
            final JButton jb1 = new JButton(DONT_SUGGEST_ANYMORE);
            buttons.add(jb1);
            this.discard.add(jb1);
            center.add(buttons);
        }
        setActionListeners(center, this.area, controller, this.lots, this.setOnSale, this.texts, this.removeFromSale);
        this.discard.forEach(l -> l.addActionListener(al3));
    }

    /**
     * 
     * @param controller
     *            controller
     * @param lot
     *            list of lots
     */
    public DiscountableFrames(final Controller controller, final List<Lot> lot) {
        final JPanel center = new JPanel(new GridLayout(lot.size(), COLS));
        setActionListeners(center, this.area, controller, this.lots, this.setOnSale, this.texts, this.removeFromSale);

        final ActionListener al = e -> {
            JButton jb = (JButton) e.getSource();
            controller.removeFromLotto(this.lots.get(this.removeLot.indexOf(jb)).getId(),
                    Integer.parseInt(this.textForRemotion.get(this.removeLot.indexOf(jb)).getText()));
            this.area.get(this.removeLot.indexOf(jb)).setText(lot.get(this.removeLot.indexOf(jb)).getDescription());
        };

        for (final Lot loti : lot) {
            final JPanel buttons = new JPanel(new FlowLayout());
            this.initializaFrame(buttons, center, this.area, this.lots, this.setOnSale, this.texts, this.removeFromSale,
                    loti, DEFAUL_DISCOUNT);
            final JButton jb2 = new JButton(REMOVE_LOT);
            buttons.add(jb2);
            this.removeLot.add(jb2);
            final JTextField text3 = new JTextField(COLS);
            buttons.add(text3);
            this.textForRemotion.add(text3);
            center.add(buttons);
        }
        setActionListeners(center, this.area, controller, this.lots, this.setOnSale, this.texts, this.removeFromSale);
        this.removeLot.forEach(l -> l.addActionListener(al));
    }

    private void setActionListeners(final JPanel panel, final List<JTextArea> jArea, final Controller cont,
            final List<Lot> lot, final List<JButton> sale, final List<JTextField> text, final List<JButton> remove) {
        final ActionListener al2 = e -> {
            JButton jb = (JButton) e.getSource();
            cont.setOnSale(lot.get(sale.indexOf(jb)).getId(), Integer.parseInt(text.get(sale.indexOf(jb)).getText()));
            jArea.get(sale.indexOf(jb)).setText(lot.get(sale.indexOf(jb)).getDescription());
        };

        ActionListener al4 = e -> {
            JButton jb = (JButton) e.getSource();
            cont.removeFromSale((lot.get(remove.indexOf(jb)).getId()));
            jArea.get(remove.indexOf(jb)).setText(lot.get(remove.indexOf(jb)).getDescription());
        };
        sale.forEach(l -> l.addActionListener(al2));
        remove.forEach(l -> l.addActionListener(al4));
        getContentPane().add(panel, BorderLayout.CENTER);
        initializeSizeAndLocation();
    }

    private void initializaFrame(final JPanel buttons2, final JPanel center2, final List<JTextArea> area2,
            final List<Lot> lots2, final List<JButton> setOnSale2, final List<JTextField> texts2,
            final List<JButton> removeFromSale2, final Lot lotToPass, final Integer valueToSet) {
        final JPanel areas = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        area2.add(textArea);
        textArea.setEditable(false);
        textArea.setSize(DIM);
        final JScrollPane jsp = new JScrollPane(textArea);
        jsp.setAutoscrolls(true);
        areas.add(jsp);
        textArea.setText((lotToPass.getDescription()));
        lots2.add(lotToPass);
        center2.add(areas);
        final JButton jb = new JButton(SET_ON_SALE);
        buttons2.add(jb);
        setOnSale2.add(jb);
        final JTextField jText = new JTextField(String.valueOf(valueToSet));
        jText.setColumns(COLS);
        texts2.add(jText);
        buttons2.add(jText);
        final JLabel label = new JLabel(PERCENTAGE);
        buttons2.add(label);
        final JButton jb2 = new JButton(REMOVE_FROM_SALE);
        removeFromSale2.add(jb2);
        buttons2.add(jb2);
        center2.add(buttons2);
    }
}