package view;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * View Interface.
 */

public interface View {

    /**
     * Refresh the list of lots.
     * @param text text to refresh
     */
    void setTextInArea(final String text);

    /**
     * 
     * @param panel the new Panel to be added.
     */
    void setNewPanel(JPanel panel);
    /**
     * update the view.
     */
    void update();

}
