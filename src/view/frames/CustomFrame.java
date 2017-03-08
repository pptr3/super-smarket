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
public abstract class CustomFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4106849838457578980L;

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
    /**
     * 
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
