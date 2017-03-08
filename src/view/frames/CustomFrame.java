package view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * This will be the superclass of all the classes that use JFrame. 
 * It provides some common methods to avoid duplicated code.
 */
public abstract class CustomFrame extends JFrame {

    /**
     * Initializes Size, Location and sets the frame to be visible.
     */
    protected void initializeSizeAndLocation() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);

    }

}
