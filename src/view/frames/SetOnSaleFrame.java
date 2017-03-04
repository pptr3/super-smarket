package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import controller.ControllerImpl;
import model.Warehouse;

public class SetOnSaleFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4833819912763016964L;
    private final List<JTextField> sale = new ArrayList<>();
    private final Controller controller;
    
    public SetOnSaleFrame() {
        this.controller = new ControllerImpl(new Warehouse());
        this.setLayout(new FlowLayout());
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
            this.controller.setOnSale(Integer.parseInt(this.sale.get(0).getText()),
                    Integer.parseInt(this.sale.get(1).getText()));
            this.sale.clear();
        };

        final ActionListener al4 = e2 -> {
            this.setVisible(false);
        };
        sales.addActionListener(alRemove);
        back.addActionListener(al4);
        this.getContentPane().add(center);
        this.getContentPane().add(south);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);
        this.pack();

    }
    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }
}
