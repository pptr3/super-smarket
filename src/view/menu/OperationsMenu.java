package view.menu;


import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import controller.Controller;
import view.enums.ErrorNames;
import view.enums.OperationsNames;
import view.frames.AddLotsFrame;
import view.frames.OperationsFramesFactoryImpl;

/**
 *
 */
public class OperationsMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;
    private final JFileChooser fileChooser = new JFileChooser();
    /**
     * @param controller controller
     */

    public OperationsMenu(final Controller controller) {
        super(OperationsNames.TITLE.getName());
        JMenuItem menuItem = new JMenuItem(OperationsNames.ADD_LOT.getName());
        menuItem.addActionListener(e -> {
               new AddLotsFrame(controller);
        });
        this.add(menuItem);

        menuItem = new JMenuItem(OperationsNames.SAVE.getName());
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveFile(this.fileChooser.getSelectedFile().getPath());
                } catch (IOException e1) {
                    controller.getSubject().showMessageErrorView(ErrorNames.SOMETHING_GOES_WRONG.getName());
                }
            }
        });
        this.add(menuItem);
        final JMenuItem load = new JMenuItem(OperationsNames.LOAD_.getName());
        menuItem = load;
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.initialize((this.fileChooser.getSelectedFile().getPath()));
                    new OperationsFramesFactoryImpl().getListOfLots(controller, controller.getList(null));
                } catch (Exception e1) {
                    controller.getSubject().showMessageErrorView(ErrorNames.ILLEGAL_FORMAT_FILE.getName());
                }
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem(OperationsNames.RESET_SUGGESTIONS.getName());
        menuItem.addActionListener(e -> {
            controller.resetSuggestions();
        });
        this.add(menuItem);
    }
}