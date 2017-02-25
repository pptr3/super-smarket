package controller;

import java.util.*;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;


import java.util.stream.*;

public class ViewImpl implements MyFakeView, Observer {
    
    private Controller controller = new ControllerImpl();
    private static final long serialVersionUID = -7275450490516982922L;
    private List<String> list = new ArrayList<>(Arrays.asList("Name", "Check in date", "Expiration date",
            "Initial quantity", "Price per single item"));
    private List<Lot> lots = new ArrayList<>();
    private List<JTextField> jtext = new ArrayList<>(); 
    private JTextArea ta = new JTextArea(80,59);
    private List<JTextField> remove = new ArrayList<>();
    private List<JTextField> sale = new ArrayList<>();
    
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
       
        //prelevo la lista dei lotti
        getLots.addActionListener(e -> {
            JFrame thirdFrame = new JFrame();
            mainFrame.setVisible(false);
            thirdFrame.setVisible(true);
            thirdFrame.setSize(800, 700);
            thirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thirdFrame.setLayout(new BorderLayout());
            JPanel panel = new JPanel();
            this.ta.setLineWrap(true);
            this.ta.setEditable(true);
            for (int i = 0; i < this.controller.getList().size(); i++) {
                    this.ta.setText(this.controller.getList().toString());
            }
            this.ta.setEditable(false);
            
            JButton jb = new JButton("Back");
            panel.add(jb, BorderLayout.NORTH);
            panel.add(this.ta);
            ActionListener al = e3 -> {
                thirdFrame.setVisible(false);
                mainFrame.setVisible(true);
            };
            jb.addActionListener(al);
            thirdFrame.getContentPane().add(panel);
        });
        /*
         aggiungo lotto
         */
        add.addActionListener(e -> {

            JPanel center = new JPanel(new GridLayout(0, 2));
            for (int i = 0; i < this.list.size(); i++) {
                center.add(wrapperPanel(new JLabel(this.list.get(i)), FlowLayout.LEFT));
                JTextField jText = new JTextField(20);
                this.jtext.add(jText);
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

            JButton confirm = new JButton("Confirm");
            panel.add(confirm);
            
            ActionListener al2 = e3 -> {
               
               Lot l = new LotBuilder()
                        .name(this.jtext.get(0).getText())
                        .checkInDate(new MyCustomDateImpl(2017,2,3))
                        .expirationDate(new MyCustomDateImpl(2017,2,13))
                        .quantity(Integer.parseInt(this.jtext.get(3).getText()))
                        .pricePerSingleItem(Integer.parseInt(this.jtext.get(4).getText()))
                        .build();
               this.controller.addLotto(l);
               this.jtext.clear();
               mainFrame.setVisible(true);
               secondFrame.setVisible(false);
              
            };
            confirm.addActionListener(al2);
            
            ActionListener al = e2 -> {
                mainFrame.setVisible(true);
                secondFrame.setVisible(false);
            };
            turnBack.addActionListener(al);
            secondFrame.getContentPane().add(center, BorderLayout.NORTH);
            secondFrame.getContentPane().add(panel, BorderLayout.SOUTH);
            secondFrame.pack();
        });
        // Remove from lots
        remove.addActionListener(e -> {
            JFrame fourthFrame = new JFrame();
            mainFrame.setVisible(false);
            fourthFrame.setVisible(true);
            fourthFrame.setSize(400, 500);
            fourthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fourthFrame.setLayout(new FlowLayout());
            JPanel center = new JPanel(new GridLayout(0, 2));
            for (int i = 0; i < 2; i++) {
                if(i==0) {
                    center.add(wrapperPanel(new JLabel("ID:"), FlowLayout.LEFT));
                } else {
                    center.add(wrapperPanel(new JLabel("How much:"), FlowLayout.LEFT));
                }
                JTextField jText = new JTextField(3);
                this.remove.add(jText);
                center.add(wrapperPanel(jText, FlowLayout.CENTER));
            }

            JButton confirm = new JButton("Confirm Remotion");

            JButton back = new JButton("Back");
            JPanel south = new JPanel();
            south.add(back, BorderLayout.SOUTH);
            south.add(confirm, BorderLayout.SOUTH);

            ActionListener alRemove = e3 ->{
//                System.out.println(Integer.parseInt(this.remove.get(0).getText()));
//                System.out.println(Integer.parseInt(this.remove.get(1).getText()));
                this.controller.removeFromLotto(Integer.parseInt(this.remove.get(0).getText()),
                        Integer.parseInt(this.remove.get(1).getText()));
                this.remove.clear();
                mainFrame.setVisible(true);
                fourthFrame.setVisible(false);
            };
            
            ActionListener al = e2 -> {
                mainFrame.setVisible(true);
                fourthFrame.setVisible(false);
            };
            confirm.addActionListener(alRemove);
            back.addActionListener(al);
            fourthFrame.getContentPane().add(center);
            fourthFrame.getContentPane().add(south);
            fourthFrame.pack();
        });
        
        setOnSale.addActionListener( al -> {
            JFrame fifthFrame = new JFrame();
            fifthFrame.setVisible(false);
            fifthFrame.setVisible(true);
            fifthFrame.setSize(800, 700);
            fifthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fifthFrame.setLayout(new FlowLayout());
            JPanel center = new JPanel(new GridLayout(0, 2));
            for (int i = 0; i < 2; i++) {
                if(i==0) {
                    center.add(wrapperPanel(new JLabel("ID:"), FlowLayout.LEFT));
                } else {
                    center.add(wrapperPanel(new JLabel("% Sale:"), FlowLayout.LEFT));
                }
                JTextField jText = new JTextField(3);
                this.sale.add(jText);
                center.add(wrapperPanel(jText, FlowLayout.CENTER));
            }

            JButton sale = new JButton("Confirm Sale");

            JButton back = new JButton("Back");
            JPanel south = new JPanel();
            south.add(back, BorderLayout.SOUTH);
            south.add(sale, BorderLayout.SOUTH);

            ActionListener alRemove = e3 ->{
                this.controller.setOnSale(Integer.parseInt(this.sale.get(0).getText()),
                        Integer.parseInt(this.sale.get(1).getText()));
                this.sale.clear();
                mainFrame.setVisible(true);
                fifthFrame.setVisible(false);
            };
            
            ActionListener al4 = e2 -> {
                mainFrame.setVisible(true);
                fifthFrame.setVisible(false);
            };
            sale.addActionListener(alRemove);
            back.addActionListener(al4);
            fifthFrame.getContentPane().add(center);
            fifthFrame.getContentPane().add(south);
            fifthFrame.pack();
            
        });

    }

    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }

    @Override
    public void discountAdvice(Map<Lot, Integer> lotti) {
        // TODO Auto-generated method stub

    }
    
    public static void main(String[] args) {
        new ViewImpl();
    }

    @Override
    public void update() {
        System.out.println("I has been notified");
        
    }
}
