package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controller.Controller;
import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;
import model.modifylists.ModifyListFactoryImpl;
import view.View;

/**
 *
 */
public class AddLotsFrame extends JFrame {

    private static final long serialVersionUID = 6784928917074846594L;
    private final List<String> list = new ArrayList<>(
            Arrays.asList("Name", "Check in date", "Expiration date", "Initial quantity", "Price per single item"));
    private final List<JTextField> jtext = new ArrayList<>();
    private String allLots= "";
    
    /**
     * @param view 
     *            view
     * @param controller
     *            controller
     */
    public AddLotsFrame(final View view, final Controller controller) {
        final JPanel center = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < this.list.size(); i++) {
            center.add(wrapperPanel(new JLabel(this.list.get(i)), FlowLayout.LEFT));
            final JTextField jText = new JTextField(20);
            this.jtext.add(jText);
            center.add(wrapperPanel(jText, FlowLayout.CENTER));
        }
        this.setTitle("Add lot");
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton turnBack = new JButton("Back");
        panel.add(turnBack);

        JButton confirm = new JButton("Confirm");
        panel.add(confirm);
        /*
         * need to refactor this part of builing lot
         */
        final ActionListener al = e -> {
            Lot l = new LotBuilder().name(this.jtext.get(0).getText())
                    .expirationDate(
                            new MyCustomDateImpl(LocalDate.now(), Integer.parseInt(this.jtext.get(2).getText())))
                    .quantity(Integer.parseInt(this.jtext.get(3).getText()))
                    .pricePerSingleItem(Integer.parseInt(this.jtext.get(4).getText()))
                    .build();
            controller.addLotto(l);
            controller.getList(null).forEach(l2 -> allLots += l2.getDescription());
            view.setTextInArea(allLots);
            this.jtext.clear();

            this.setVisible(false);
        };

        final ActionListener al2 = e -> {
            this.setVisible(false);
        };

        confirm.addActionListener(al);
        turnBack.addActionListener(al2);
        this.getContentPane().add(center, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.SOUTH);
        this.pack();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);

    }

    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }
}
