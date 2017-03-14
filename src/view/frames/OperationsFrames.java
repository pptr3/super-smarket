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
import controller.enums.ErrorNames;
import controller.enums.OperationsNames;
import model.Lot;
import model.discountstrategies.DiscountStrategy;

/**
 * 
 *
 */
public class OperationsFrames extends CustomFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 5926371999570025570L;
    private static final Integer DEFAUL_DISCOUNT = 10;
    private static final Integer COLS = 2;
    private static final Dimension DIM = Toolkit.getDefaultToolkit().getScreenSize();
    private final List<JTextField> texts = new ArrayList<>();
    private final List<JTextField> textForRemotion = new ArrayList<>();
    private final List<JButton> discard = new ArrayList<>();
    private final List<JButton> setOnSale = new ArrayList<>();
    private final List<JButton> removeLot = new ArrayList<>();
    private final List<JButton> removeFromSale = new ArrayList<>();
    private final List<Lot> lots = new ArrayList<>();
    private final List<JTextArea> area = new ArrayList<>();
    private final Controller control;
    private final BasicOperationsOnLots test = new BasicOperationsOnLots();
    /**
     * 
     * @param controller
     *            controller
     * @param ds
     *            discount strategy
     */
    public OperationsFrames(final Controller controller, final DiscountStrategy ds) {
        this.control = controller;
        final Map<Lot, Integer> map = controller.getDiscountable(ds);
        if (map.isEmpty()) {
            controller.getSubject().showMessageErrorView(ErrorNames.NO_LOTS.getName());
            return;
        }
        final JPanel center = new JPanel(new GridLayout(map.size(), COLS));

        final ActionListener al3 = e -> {
            JButton jb = (JButton) e.getSource();
            this.test.dontSuggest(controller, this.lots.get(this.discard.indexOf(jb)));
           // controller.dontSuggestAnymore(this.lots.get(this.discard.indexOf(jb)));
        };

        for (final Map.Entry<Lot, Integer> lot : map.entrySet()) {
            final JPanel buttons = new JPanel(new FlowLayout());
            this.initializaFrame(buttons, center, this.area, this.lots, this.setOnSale, this.texts, this.removeFromSale, lot.getKey(), lot.getValue());
            final JButton jb1 = new JButton(OperationsNames.DONT_SUGGEST_ANYMORE.getName());
            buttons.add(jb1);
            this.discard.add(jb1);
            center.add(buttons);
        }
        setActionListeners(center, this.area, controller, this.lots, this.setOnSale, this.texts, this.removeFromSale);
        this.discard.forEach(l -> l.addActionListener(al3));
        this.add(new JScrollPane(center), BorderLayout.CENTER);
    }

    /**
     * 
     * @param controller
     *            controller
     * @param lot
     *            list of lots
     */
    public OperationsFrames(final Controller controller, final List<Lot> lot) {
        this.control = controller;
        if (lot.isEmpty()) {
            controller.getSubject().showMessageErrorView(ErrorNames.NO_LOTS.getName());
            return;
        }
        final JPanel center = new JPanel(new GridLayout(lot.size(), COLS));

        final ActionListener al = e -> {
            JButton jb = (JButton) e.getSource();
            try {
                this.test.removeFromLot(controller, this.lots.get(this.removeLot.indexOf(jb)).getId(),
                        Integer.parseInt(this.textForRemotion.get(this.removeLot.indexOf(jb)).getText()));
                this.test.updateText(this.area.get(this.removeLot.indexOf(jb)),
                        lot.get(this.removeLot.indexOf(jb)).getDescription());
            } catch (Exception e1) {
                controller.getSubject().showMessageErrorView(e1.getMessage());
            }
        };

        for (final Lot loti : lot) {
            final JPanel buttons = new JPanel(new FlowLayout());
            this.initializaFrame(buttons, center, this.area, this.lots, this.setOnSale, this.texts, this.removeFromSale,
                    loti, DEFAUL_DISCOUNT);
            final JButton jb2 = new JButton(OperationsNames.REMOVE_LOT.getName());
            buttons.add(jb2);
            this.removeLot.add(jb2);
            final JTextField text3 = new JTextField(COLS);
            buttons.add(text3);
            this.textForRemotion.add(text3);
            center.add(buttons);
        }
        setActionListeners(center, this.area, controller, this.lots, this.setOnSale, this.texts, this.removeFromSale);
        this.add(new JScrollPane(center), BorderLayout.CENTER);
        this.removeLot.forEach(l -> l.addActionListener(al));
    }

    private void setActionListeners(final JPanel panel, final List<JTextArea> jArea, final Controller cont,
            final List<Lot> lot, final List<JButton> sale, final List<JTextField> text, final List<JButton> remove) {
        final ActionListener al2 = e -> {
            try {
                final JButton jb = (JButton) e.getSource();
               this.test.setLotOnSale(cont, lot.get(sale.indexOf(jb)).getId(), Integer.parseInt(text.get(sale.indexOf(jb)).getText()));
                jArea.get(sale.indexOf(jb)).setText(lot.get(sale.indexOf(jb)).getDescription());
            } catch (Exception e2) {
                this.control.getSubject().showMessageErrorView(e2.getMessage());
            }
        };
        final ActionListener al4 = e -> {
            try {
                final JButton jb = (JButton) e.getSource();
                this.test.removeFromSale(cont, (lot.get(remove.indexOf(jb)).getId()));
                this.test.updateText(jArea.get(remove.indexOf(jb)), lot.get(remove.indexOf(jb)).getDescription());
            } catch (Exception e1) {
                this.control.getSubject().showMessageErrorView(e1.getMessage());
            }
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
            final JButton jb = new JButton(OperationsNames.SET_ON_SALE.getName());
            buttons2.add(jb);
            setOnSale2.add(jb);
            final JTextField jText = new JTextField(String.valueOf(valueToSet));
            jText.setColumns(COLS);
            texts2.add(jText);
            buttons2.add(jText);
            final JLabel label = new JLabel(OperationsNames.PERCENTAGE.getName());
            buttons2.add(label);
            final JButton jb2 = new JButton(OperationsNames.REMOVE_FROM_SALE.getName());
            removeFromSale2.add(jb2);
            buttons2.add(jb2);
            center2.add(buttons2);
    }
}