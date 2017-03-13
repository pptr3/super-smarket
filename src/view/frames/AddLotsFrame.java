package view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
import model.MyCustomDate;
import model.MyCustomDateImpl;
import view.enums.ErrorNames;
import view.enums.LotFeatures;
import view.enums.OperationsNames;

/**
 *
 */
public class AddLotsFrame extends CustomFrame {

    private static final long serialVersionUID = 6784928917074846594L;
    private static final Integer START = 5;
    private static final Integer END = 7;
    private final List<LotFeatures> list = new ArrayList<>(Arrays.asList(LotFeatures.NAME, LotFeatures.CHECK_IN_DATE,
            LotFeatures.EXPIRATION_DATE, LotFeatures.INITIAL_QUANTITY, LotFeatures.PRICE_PER_SINGLE_ITEM));
    private final List<JTextField> jtext = new ArrayList<>();

    /**
     * @param controller
     *            controller
     */
    public AddLotsFrame(final Controller controller) {
        final JPanel center = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < this.list.size(); i++) {
            center.add(wrapperPanel(new JLabel(this.list.get(i).getName()), FlowLayout.LEFT));
            final JTextField jText = new JTextField(20);
            this.jtext.add(jText);
            center.add(wrapperPanel(jText, FlowLayout.CENTER));
        }
        this.setTitle(OperationsNames.ADD_LOT.getName());
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        final JButton turnBack = new JButton(OperationsNames.BACK.getName());
        panel.add(turnBack);

        final JButton confirm = new JButton(OperationsNames.CONFIRM.getName());
        panel.add(confirm);
        /*
         * need to refactor this part of builing lot
         */
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
                controller.getSubject().showMessageErrorView(ErrorNames.FORMAT.getName());
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

    private MyCustomDate initializeDate(final List<JTextField> text, final Integer index) {
        return new MyCustomDateImpl(Integer.parseInt(text.get(index).getText().substring(0, 4)),
                Integer.parseInt(text.get(index).getText().substring(START, END)),
                Integer.parseInt(text.get(index).getText().substring(8, 10)));
    }
}
