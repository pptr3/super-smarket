package controller;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Lot;
import model.Pair;

import java.util.stream.*;

public class ViewImpl implements MyFakeView {

    private static final long serialVersionUID = -7275450490516982922L;
    private List<String> list = new ArrayList<>(Arrays.asList("ID","Name", "Check in date", "Expiration date",
            "Initial quantity", "Price per single item"));
    
    public ViewImpl() {
        JFrame mainFrame = new JFrame();
        // Inizializzazione base
        mainFrame.setSize(500, (50 * this.list.size()));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        // Pannello sud, ossia in basso
        JPanel mainPanel = new JPanel(new FlowLayout());
        JButton add = new JButton("Add Lots");
        JButton remove = new JButton("Remove from lots");
        JButton getLots = new JButton("Get list of lots");
        JButton setOnSale = new JButton("Set on sale");
        mainPanel.add(add);
        mainPanel.add(remove);
        mainPanel.add(getLots);
        mainPanel.add(setOnSale);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();
        // Handler pulsante ok

        add.addActionListener(e -> {

            JPanel center = new JPanel(new GridLayout(0, 2));
            for (int i = 0; i < this.list.size(); i++) {
                center.add(wrapperPanel(new JLabel(this.list.get(i)), FlowLayout.LEFT));
                JTextField jText = new JTextField(20);
                center.add(wrapperPanel(jText, FlowLayout.CENTER));
            }

            mainFrame.setVisible(false);
            JFrame secondFrame = new JFrame();
            secondFrame.setVisible(true);
            secondFrame.setSize(500, 600);
            secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            secondFrame.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JButton turnBack = new JButton("Back");
            panel.add(turnBack);

            ActionListener al = e2 -> {
                mainFrame.setVisible(true);
                secondFrame.setVisible(false);
            };
            turnBack.addActionListener(al);
            secondFrame.getContentPane().add(center, BorderLayout.NORTH);
            secondFrame.getContentPane().add(panel, BorderLayout.SOUTH);
            secondFrame.pack();
            
        });

    }

    /*
     * Helper function statica per wrappare un componente in un pannellino con
     * FlowLayout Serve a garantire che il componente sia piazzato secondo le
     * sue dimentioni preferite
     */
    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }

    @Override
    public void discountAdvice(List<Pair<Lot, Integer>> lotti) {
        // TODO Auto-generated method stub

    }
    
    public static void main(String[] args) {
        new ViewImpl();
    }
}
