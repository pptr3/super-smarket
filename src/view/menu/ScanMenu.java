package view.menu;

import javax.swing.JButton;
import javax.swing.JMenu;
import controller.Controller;
import view.ResourceBound;

/**
 * Base menu.
 *
 */

public class ScanMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -2993920227347864515L;
    private final ResourceBound res = new ResourceBound();
    /**
     * 
     * @param controller controller
     */

    public ScanMenu(final Controller controller) {
        super("Scan");
        final JButton startScan = new JButton(this.res.setName("START_SCAN"));
        final JButton stopScan = new JButton(this.res.setName("STOP_SCAN"));
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