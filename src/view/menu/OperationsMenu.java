package view.menu;


import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import model.resourcebundle.ResourceBound;
import view.frames.AddLotsFrame;
import view.frames.OperationsFramesFactoryImpl;

/**
 * Base OperationsMenu of View.
 */
public class OperationsMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;
    private final JFileChooser fileChooser = new JFileChooser();
    private final ResourceBound res = new ResourceBound();
    /**
     * @param controller controller
     */

    public OperationsMenu(final Controller controller) {
        super("Operations");
        final JMenuItem load = new JMenuItem(this.res.setName("LOAD"));
        JMenuItem menuItem = new JMenuItem(this.res.setName("ADD_LOT"));
        menuItem.addActionListener(e -> {
            new AddLotsFrame(controller, load);
        });
        this.add(menuItem);

        menuItem = load;
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.initialize((this.fileChooser.getSelectedFile().getPath()));
                    new OperationsFramesFactoryImpl().getListOfLots(controller, controller.getList(null));
                } catch (Exception e1) {
                    controller.getSubject().showMessageErrorView(this.res.setName("ILLEGAL_FORMAT_FILE"));
                }
                checkEnable(controller.getList(null).size(), load);
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem(this.res.setName("SAVE"));
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveFile(this.fileChooser.getSelectedFile().getPath());
                } catch (IOException e1) {
                    controller.getSubject().showMessageErrorView(this.res.setName("SOMETHING_GOES_WRONG"));
                }
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem(this.res.setName("RESET_SUGGESTIONS"));
        menuItem.addActionListener(e -> {
            controller.resetSuggestions();
        });
        this.add(menuItem);
    }

    /**
     * 
     * @param size
     *            size to check
     * @param load
     *            load
     */
    public static void checkEnable(final Integer size, final JMenuItem load) {
        if (size > 0) {
            load.setEnabled(false);
        }
    }
}