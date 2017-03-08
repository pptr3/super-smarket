package view.frames;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import view.View;

/*
 * TODO NOTE: this class and GetDiscountableWithinAWeekFrame are almost the same, refactor
 * TODO Manca la ScrollBar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

/**
 * 
 *
 */
public class GetDiscountableFrames extends CustomFrame {

    /**
     * 
     */
    private static final String SET_ON_SALE = "Set on sale";
    private static final String BACK = "Back";
    private static final String PERCENTAGE = "%";
    private static final long serialVersionUID = 5926371999570025570L;
    private static final Integer COLS = 2;
    private java.util.List<JTextField> texts = new ArrayList<>();
  //  private java.util.List<JButton> discard = new ArrayList<>();
    private java.util.List<JButton> sale = new ArrayList<>();
    private java.util.List<JButton> backs = new ArrayList<>();
    private java.util.List<Lot> lots = new ArrayList<>();
    private java.util.List<JTextArea> area = new ArrayList<>();

    /**
     * 
     * @param controller
     *            controller
     * @param view
     *            view
     * @param ds
     *            discount strategy
     */
    public GetDiscountableFrames(final View view, final Controller controller, final DiscountStrategy ds) {
        this.setTitle("Over fifty discount");
        final Map<Lot, Integer> map = controller.getDiscountable(ds);
        int rows = map.size();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel center = new JPanel(new GridLayout(rows, COLS));
        final ActionListener al1 = e -> {
            this.setVisible(false);
        };

        final ActionListener al2 = e -> {
            JButton jb = (JButton) e.getSource();
            controller.setOnSale(this.lots.get(this.sale.indexOf(jb)).getId(),
                    Integer.parseInt(this.texts.get(this.sale.indexOf(jb)).getText()));
            this.area.get(this.sale.indexOf(jb)).setText(String.valueOf(this.lots.get(this.sale.indexOf(jb))));
        };
        // Lo commento perchè per me in questo frame non serve, seve soltanto
        // nella Scan
        // ActionListener al3 = e -> {
        // JButton jb = (JButton) e.getSource();
        // controller.dontSuggestAnymore(this.lots.get(this.discard.indexOf(jb)));
        // };
        for (final Map.Entry<Lot, Integer> lot : map.entrySet()) {
            final JPanel areas = new JPanel(new BorderLayout());
            final JTextArea textArea = new JTextArea();
            this.area.add(textArea);
            textArea.setEditable(false);
            textArea.setSize(dim);
            final JScrollPane jsp = new JScrollPane(textArea);
            jsp.setAutoscrolls(true);
            areas.add(jsp);
            textArea.setText(String.valueOf(lot.getKey()));
            this.lots.add(lot.getKey());
            center.add(areas);
            final JPanel buttons = new JPanel(new FlowLayout());
            final JButton jb = new JButton(SET_ON_SALE);
            buttons.add(jb);
            this.sale.add(jb);
            final JTextField jText = new JTextField(String.valueOf(lot.getValue()));
            jText.setColumns(COLS);
            this.texts.add(jText);
            buttons.add(jText);
            final JLabel label = new JLabel(PERCENTAGE);
            buttons.add(label);
//            final JButton jb1 = new JButton("Don't suggest anymore");
//            buttons.add(jb1);
//            this.discard.add(jb1);
            final JButton back = new JButton(BACK);
            this.backs.add(back);
            buttons.add(back);
            center.add(buttons);
        }
        this.backs.forEach(l -> l.addActionListener(al1));
        this.sale.forEach(l -> l.addActionListener(al2));
        // this.discard.forEach(l -> l.addActionListener(al3));
        this.getContentPane().add(center, BorderLayout.CENTER);
        initializeSizeAndLocation();
    }
    /*
     * FOR TESTS use this:
     * public GetDiscountableOverFiftyDiscountFrame(final View view, final Controller controller) {
        this.setTitle("Over fifty");
        // final Map<Lot, Integer> map = controller.getDiscountable(new
        // DiscountStrategyFactoryImpl().overFiftyDiscount());
        Lot l1 = new LotBuilder().name("test").expirationDate(new MyCustomDateImpl(LocalDate.now(), 1)).quantity(1)
                .pricePerSingleItem(1).build();
        Lot l2 = new LotBuilder().name("test2").expirationDate(new MyCustomDateImpl(LocalDate.now(), 1)).quantity(1)
                .pricePerSingleItem(1).build();
//        controller.addLotto(l1);
//        controller.addLotto(l2);
        final Map<Lot, Integer> map = controller.getDiscountable(null);
//        map.put(l1, 10);
//        map.put(l2, 20);
        int rows = map.size();
        System.out.println(rows);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel center = new JPanel(new GridLayout(rows, COLS));

        ActionListener al1 = e -> {
            this.setVisible(false);
        };
        ActionListener al2 = e -> {
            JButton jb = (JButton) e.getSource();
            controller.setOnSale(this.lots.get(this.sale.indexOf(jb)).getId(),
                    Integer.parseInt(this.texts.get(this.sale.indexOf(jb)).getText()));
            this.area.get(this.sale.indexOf(jb)).setText(String.valueOf(this.lots.get(this.sale.indexOf(jb))));
        };

        for (Map.Entry<Lot, Integer> lot : map.entrySet()) {
            JPanel areas = new JPanel(new BorderLayout());
            JTextArea textArea = new JTextArea();
            this.area.add(textArea);
            textArea.setEditable(false);
            textArea.setSize(dim);
            final JScrollPane jsp = new JScrollPane(textArea);
            jsp.setAutoscrolls(true);
            areas.add(jsp);
            // for testing
            textArea.setText(String.valueOf(lot.getKey()));
            this.lots.add(lot.getKey());
            center.add(areas);
                JPanel buttons = new JPanel(new FlowLayout());
                JButton jb = new JButton("Ok set on sale");
                buttons.add(jb);
                this.sale.add(jb);
                // 20 is the suggested disount, this is for testing, when i
                // finish i have to change with the Integer of the map
                JTextField jText = new JTextField(String.valueOf(lot.getValue()));
                jText.setColumns(2);
                this.texts.add(jText);
                buttons.add(jText);
                JLabel label = new JLabel("%");
                buttons.add(label);
                JButton jb1 = new JButton("Don't ask me again");
                buttons.add(jb1);
                this.discard.add(jb1);
                JButton back = new JButton("Back");
                this.backs.add(back);
                buttons.add(back);
                center.add(buttons);
        }
        this.backs.forEach(l -> l.addActionListener(al1));
        this.sale.forEach(l -> l.addActionListener(al2));
        this.getContentPane().add(center, BorderLayout.CENTER);
        initializeSizeAndLocation();
    }
    */
}