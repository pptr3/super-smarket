package view.menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;

/**
 * Base menu.
 *
 */

public class ScanMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    /**
     * 
     * @param frame frame
     */

    public ScanMenu(final JFrame frame) {
        super("Scan");
        JButton startScan = new JButton("Start Scan");
        startScan.addActionListener(e -> {
               //action listener per get lots
        });
        this.add(startScan);
        JButton stopScan = new JButton("Stop Scan");
        startScan.addActionListener(e -> {
               //action listener per get lots
        });
        this.add(stopScan);
    }

}