//Swing Toolbar with Buttons

import javax.swing.*;
import java.awt.event.*;

public class ToolBarExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Toolbar Example");

        // Create Toolbar
        JToolBar toolBar = new JToolBar("My Toolbar");

        // Create Buttons
        JButton newButton = new JButton("New");
        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");

        // Add Action Listeners
        newButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "New File Created"));
        openButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "File Opened"));
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "File Saved"));

        // Add Buttons to Toolbar
        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);

        // Add Toolbar to Frame
        frame.add(toolBar, "North");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
