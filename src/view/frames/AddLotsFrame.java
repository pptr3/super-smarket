package view.frames;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.Controller;
import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;
import view.View;

/**
 *
 */
public class AddLotsFrame extends CustomFrame {

    private static final long serialVersionUID = 6784928917074846594L;
    private final List<String> list = new ArrayList<>(
            Arrays.asList("Name", "Expiration date", "Check in date", "Initial quantity", "Price per single item"));
    private final List<JTextField> jtext = new ArrayList<>();

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
                    .expirationDate(new MyCustomDateImpl(Integer.parseInt(this.jtext.get(1).getText().substring(0, 4)),
                            Integer.parseInt(this.jtext.get(1).getText().substring(5, 7)),
                            Integer.parseInt(this.jtext.get(1).getText().substring(8, 10))))
                    .checkInDate(new MyCustomDateImpl(Integer.parseInt(this.jtext.get(1).getText().substring(0, 4)),
                            Integer.parseInt(this.jtext.get(1).getText().substring(5, 7)),
                            Integer.parseInt(this.jtext.get(1).getText().substring(8, 10))))
                    .quantity(Integer.parseInt(this.jtext.get(3).getText()))
                    .pricePerSingleItem(Integer.parseInt(this.jtext.get(4).getText())).build();
            controller.addLotto(l);
            this.setVisible(false);
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
}
