package view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import view.View;

/**
 * Class that removes products from lots.
 *
 */
public class RemoveLotsFrame extends CustomFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6784928917074846594L;
    private final List<JTextField> removes = new ArrayList<>();
    private String allLots = "";
    /**
     * @param view 
     *            view
     * @param controller
     *            controller
     */
    public RemoveLotsFrame(final View view, final Controller controller) {
        this.setLayout(new FlowLayout());
        this.setTitle("Remove lot");
        final JPanel center = new JPanel(new GridLayout(0, 2));

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                center.add(wrapperPanel(new JLabel("ID:"), FlowLayout.LEFT));
            } else {
                center.add(wrapperPanel(new JLabel("How much:"), FlowLayout.LEFT));
            }
            final JTextField jText = new JTextField(3);
            this.removes.add(jText);
            center.add(wrapperPanel(jText, FlowLayout.CENTER));
        }

        final JButton confirm = new JButton("Confirm Remotion");

        final JButton back = new JButton("Back");
        final JPanel south = new JPanel();
        south.add(back, BorderLayout.SOUTH);
        south.add(confirm, BorderLayout.SOUTH);

        final ActionListener alRemove = e3 -> {
            controller.removeFromLotto(Integer.parseInt(this.removes.get(0).getText()),
                    Integer.parseInt(this.removes.get(1).getText()));
            controller.getList(null).forEach(l2 -> allLots += l2.getDescription());
            view.setTextInArea(allLots);
            this.removes.clear();
            this.setVisible(false);
        };

        final ActionListener al = e2 -> {
            this.setVisible(false);
        };
        confirm.addActionListener(alRemove);
        back.addActionListener(al);
        this.getContentPane().add(center);
        this.getContentPane().add(south);
        this.initializeSizeAndLocation();
        this.pack();
    }
}
