package view.menu;

import javax.swing.JButton;
import javax.swing.JMenu;
import controller.Controller;
import controller.enums.ScanNames;
import controller.enums.TitlesNames;

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
        super(TitlesNames.SCAN_TITLE.getName());
        final JButton startScan = new JButton(ScanNames.START_SCAN.getName());
        final JButton stopScan = new JButton(ScanNames.STOP_SCAN.getName());
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