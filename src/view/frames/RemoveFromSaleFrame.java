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
import view.View;

/**
 * TODO
 * README: the codes of this package are really similar, to refactor.
 *
 */
public class RemoveFromSaleFrame extends CustomFrame {

   /**
    * 
    */
   private static final long serialVersionUID = -4833819912763016964L;
   private final List<JTextField> sale = new ArrayList<>();
   private String allLots = "";
   /**
    * 
    * @param controller
    *            controller
    */
   public RemoveFromSaleFrame(final View view, final Controller controller) {
       this.setLayout(new FlowLayout());
       this.setTitle("Remove Lot on sale");
       final JPanel center = new JPanel(new GridLayout(0, 2));
               center.add(wrapperPanel(new JLabel("ID:"), FlowLayout.LEFT));
           final JTextField jText = new JTextField(3);
           this.sale.add(jText);
           center.add(wrapperPanel(jText, FlowLayout.CENTER));
 

       final JButton sales = new JButton("Confirm Sale");

       final JButton back = new JButton("Back");
       final JPanel south = new JPanel();
       south.add(back, BorderLayout.SOUTH);
       south.add(sales, BorderLayout.SOUTH);

       final ActionListener alRemove = e3 -> {
           controller.removeFromSale(Integer.parseInt(this.sale.get(0).getText()));
           controller.getList(null).forEach(l2 -> allLots += l2.getDescription());
           view.setTextInArea(allLots);
           this.sale.clear();
           this.setVisible(false);
       };

       final ActionListener al4 = e2 -> {
           this.setVisible(false);
       };
       sales.addActionListener(alRemove);
       back.addActionListener(al4);
       this.getContentPane().add(center);
       this.getContentPane().add(south);
       this.initializeSizeAndLocation();
       this.pack();

   }

   private static JPanel wrapperPanel(final JComponent component, final int orientation) {
       final JPanel panel = new JPanel(new FlowLayout(orientation));
       panel.add(component);
       return panel;
   }
}
