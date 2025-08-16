//Swing - Main Menu (JMenuBar, JMenu, JMenuItem)

import javax.swing.*;
import java.awt.event.*;

public class MainMenuExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu Example");

        // Create Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // Create Menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        // Create Menu Items
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action to Exit
        exitItem.addActionListener(e -> System.exit(0));

        // Add items to File menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator(); // separator line
        fileMenu.add(exitItem);

        // Add menus to MenuBar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // Set MenuBar
        frame.setJMenuBar(menuBar);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
