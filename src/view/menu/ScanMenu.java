package view.menu;

import javax.swing.JButton;
import javax.swing.JMenu;
import controller.Controller;

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
     * @param controller controller
     */

    public ScanMenu(final Controller controller) {
        super("Scan");
        JButton startScan = new JButton("Start Scan");
        JButton stopScan = new JButton("Stop Scan");
        stopScan.setEnabled(false);
        startScan.addActionListener(e -> {
            controller.startScan();
            startScan.setEnabled(false);
            stopScan.setEnabled(true);
        });
        this.add(startScan);
        stopScan.addActionListener(e -> {
            controller.stopScan();
            startScan.setEnabled(true);
            stopScan.setEnabled(false);
        });
        this.add(stopScan);
    }

}