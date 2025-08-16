//Toolbar with Icons (Using default Swing icons or custom image icons)

import javax.swing.*;

public class ToolBarWithIcons {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Toolbar with Icons");

        // Create Toolbar
        JToolBar toolBar = new JToolBar("My Toolbar");

        // Load Icons (replace with your local images if needed)
        JButton cutButton = new JButton(new ImageIcon("cut.png"));
        JButton copyButton = new JButton(new ImageIcon("copy.png"));
        JButton pasteButton = new JButton(new ImageIcon("paste.png"));

        // Add Tooltip for better UX
        cutButton.setToolTipText("Cut");
        copyButton.setToolTipText("Copy");
        pasteButton.setToolTipText("Paste");

        // Add Buttons to Toolbar
        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);

        // Add Toolbar to Frame
        frame.add(toolBar, "North");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
