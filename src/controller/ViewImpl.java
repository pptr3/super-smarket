package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import model.Lot;
import model.LotBuilder;
import model.MyCustomDateImpl;

/**
 * Implementation of MyFakeView for testing my Controller.
 *
 */
public class ViewImpl implements MyFakeView {

    private static final Integer SIZE_X = 80;
    private static final Integer SIZE_Y = 59;
    private static final Integer SIZE_Y2 = 800;
    private static final Integer SIZE_X2 = 700;
    private static final Integer LENGTH_TEXT_FIELD = 20;
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;

    private final Controller controller;
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -7275450490516982922L;

    private final List<String> list = new ArrayList<>(Arrays.asList("Name", "Check in date", "Expiration date",
            "Initial quantity", "Price per single item"));
    private final List<JTextField> jtext = new ArrayList<>(); 
    private final JTextArea ta = new JTextArea(SIZE_Y, SIZE_X);
    private final List<JTextField> removes = new ArrayList<>();
    private final List<JTextField> sale = new ArrayList<>();
/**
 * 
 * @param myController myController
 */
    public ViewImpl(final Controller myController) {
        this.controller = myController;
        final JFrame mainFrame = new JFrame();
        // Inizializzazione base
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        // Pannello sud, ossia in basso
        final JPanel mainPanel = new JPanel(new FlowLayout());
        final JButton add = new JButton("Add Lots");
        final JButton remove = new JButton("Remove from lots");
        final JButton getLots = new JButton("Get list of lots");
        final JButton setOnSale = new JButton("Set on sale");
        final JButton startScan = new JButton("Start scan");
        final JButton stopScan = new JButton("Stop scan");
        mainPanel.add(startScan);
        mainPanel.add(stopScan);
        mainPanel.add(add);
        mainPanel.add(remove);
        mainPanel.add(getLots);
        mainPanel.add(setOnSale);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();

        startScan.addActionListener(e -> {
            this.controller.startScan();
            startScan.setEnabled(false);
            stopScan.setEnabled(true);
        });

        stopScan.addActionListener(e -> {
            this.controller.stopScan();
            startScan.setEnabled(true);
            stopScan.setEnabled(false);
        });

        //prelevo la lista dei lotti
        getLots.addActionListener(e -> {
            final JFrame thirdFrame = new JFrame();
            mainFrame.setVisible(false);
            thirdFrame.setVisible(true);
            thirdFrame.setSize(SIZE_Y2, SIZE_X2);
            thirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thirdFrame.setLayout(new BorderLayout());
            final JPanel panel = new JPanel();
            this.ta.setLineWrap(true);
            this.ta.setEditable(true);
            for (int i = 0; i < this.controller.getList().size(); i++) {
                    this.ta.setText(this.controller.getList().toString());
            }
            this.ta.setEditable(false);

            final JButton jb = new JButton("Back");
            panel.add(jb, BorderLayout.NORTH);
            panel.add(this.ta);
            final ActionListener al = e3 -> {
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

            final JPanel center = new JPanel(new GridLayout(0, 2));
            for (int i = 0; i < this.list.size(); i++) {
                center.add(wrapperPanel(new JLabel(this.list.get(i)), FlowLayout.LEFT));
                final JTextField jText = new JTextField(LENGTH_TEXT_FIELD);
                this.jtext.add(jText);
                center.add(wrapperPanel(jText, FlowLayout.CENTER));
            }

            mainFrame.setVisible(false);
            final JFrame secondFrame = new JFrame();
            secondFrame.setVisible(true);
            secondFrame.setSize(SIZE_Y2, SIZE_X2);
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
                        .checkInDate(new MyCustomDateImpl(0, 0, 0))
                        .expirationDate(new MyCustomDateImpl(0, 0, 0))
                        .quantity(Integer.parseInt(this.jtext.get(3).getText()))
                        .pricePerSingleItem(Integer.parseInt(this.jtext.get(4).getText()))
                        .build();
               this.controller.addLotto(l);
               this.jtext.clear();
               mainFrame.setVisible(true);
               secondFrame.setVisible(false);

            };
            confirm.addActionListener(al2);

            final ActionListener al = e2 -> {
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
            final JFrame fourthFrame = new JFrame();
            mainFrame.setVisible(false);
            fourthFrame.setVisible(true);
            fourthFrame.setSize(SIZE_Y2, SIZE_X2);
            fourthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fourthFrame.setLayout(new FlowLayout());
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
//                System.out.println(Integer.parseInt(this.remove.get(0).getText()));
//                System.out.println(Integer.parseInt(this.remove.get(1).getText()));
                this.controller.removeFromLotto(Integer.parseInt(this.removes.get(0).getText()),
                        Integer.parseInt(this.removes.get(1).getText()));
                this.removes.clear();
                mainFrame.setVisible(true);
                fourthFrame.setVisible(false);
            };

            final ActionListener al = e2 -> {
                mainFrame.setVisible(true);
                fourthFrame.setVisible(false);
            };
            confirm.addActionListener(alRemove);
            back.addActionListener(al);
            fourthFrame.getContentPane().add(center);
            fourthFrame.getContentPane().add(south);
            fourthFrame.pack();
        });

        setOnSale.addActionListener(al -> {
            final JFrame fifthFrame = new JFrame();
            fifthFrame.setVisible(false);
            fifthFrame.setVisible(true);
            fifthFrame.setSize(SIZE_Y2, SIZE_X2);
            fifthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fifthFrame.setLayout(new FlowLayout());
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
                mainFrame.setVisible(true);
                fifthFrame.setVisible(false);
            };

            final ActionListener al4 = e2 -> {
                mainFrame.setVisible(true);
                fifthFrame.setVisible(false);
            };
            sales.addActionListener(alRemove);
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
    public void discountAdvice(final Map<Lot, Integer> lotti) {
        // TODO Auto-generated method stub

    }
/**
 * 
 */
    public void update() {
        try {
            SwingUtilities.invokeAndWait(() -> JOptionPane.showMessageDialog(new JFrame(), "I has been notified"));
        } catch (InvocationTargetException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
