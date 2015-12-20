import gui.GUI;

import javax.swing.*;

/**
 * @author Jimmy Maksymiw
 */
public class StartGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().start());
    }

}
