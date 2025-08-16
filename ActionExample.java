/*An Action in Swing is an object that encapsulates both the behavior (code to run) and properties (name, icon, shortcut, tooltip, etc.) of a command.
Instead of writing separate ActionListeners for every button, menu item, or toolbar button, you can define a single Action and attach it everywhere.
Why Use Actions?
Centralizes logic → same code can be used for menu items, toolbar buttons, keyboard shortcuts.
Stores metadata → text, icon, tooltip, accelerator (Ctrl+S, etc.).
More maintainable for large applications. */

import javax.swing.*;
import java.awt.event.*;

public class ActionExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Action Example");

        // Define an Action
        Action saveAction = new AbstractAction("Save", new ImageIcon("save.png")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "File Saved!");
            }
        };
        saveAction.putValue(Action.SHORT_DESCRIPTION, "Save the file"); // tooltip
        saveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S")); // shortcut

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem(saveAction)); // attach Action directly
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.add(saveAction); // attach Action directly
        frame.add(toolBar, "North");

        // Button
        JButton saveButton = new JButton(saveAction); // reuse Action
        frame.add(saveButton);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
