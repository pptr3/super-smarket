package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextArea;


import controller.Controller;
import controller.ControllerImpl;
import model.Warehouse;
/**
 *
 */
public class GetLotsFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6048132090119838798L;
    private final JTextArea ta = new JTextArea(500, 500);
    private final Controller controller;
/**
 * 
 * @param frame
 */
    public GetLotsFrame(final JFrame frame) {
/* fare dopo questa parte, prima dobbiamo impostare la struttura in cui andrò a mettere la lista dei lotti */
        this.controller = new ControllerImpl(new Warehouse());
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        this.ta.setEditable(true);
        for (int i = 0; i < this.controller.getList().size(); i++) {
            this.ta.setText(this.controller.getList().toString());
        }
        this.ta.setEditable(false);
        panel.add(this.ta);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);

    }
}
