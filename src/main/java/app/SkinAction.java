package app;

import org.apache.logging.log4j.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SkinAction extends AbstractAction {

    private Component button;
    private String plaf;

    public SkinAction(Component button, String plaf){

        this.button = button;
        this.plaf = plaf;

    }

    public void actionPerformed(ActionEvent e) {

        try {
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(button);
        } catch (ClassNotFoundException e1) {
            GUI.log.log(Level.ERROR, e1.getMessage());
        } catch (InstantiationException e1) {
            GUI.log.log(Level.ERROR, e1.getMessage());
        } catch (IllegalAccessException e1) {
            GUI.log.log(Level.ERROR, e1.getMessage());
        } catch (UnsupportedLookAndFeelException e1) {
            GUI.log.log(Level.ERROR, e1.getMessage());
        }

    }
}
