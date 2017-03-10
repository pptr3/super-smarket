package view.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This will be the superclass of all the classes that use JFrame. 
 * It provides some common methods to avoid duplicated code.
 */
public class CustomFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4106849838457578980L;

    /**
     * Initializes Size, Location and sets the frame to be visible.
     */
    protected void initializeSizeAndLocation() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width), (dim.height - this.getSize().height));
        this.setVisible(true);

    }
    /**
     * Create a new panel containing a new component with the given orientation.
     * @param component component
     * @param orientation orientation
     * @return new JPanel
     */
    protected static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        return panel;
    }

}
