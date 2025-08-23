//Creating a popup menu in Java Swing. 
//A popup menu typically appears when you right-click on a component
/*
Steps to Create a Popup Menu
Create a JPopupMenu instance.
This represents the popup menu itself.
Create JMenuItems and add them to the popup menu.
Each JMenuItem represents a selectable option.
Add action listeners to handle clicks on menu items.
Attach the popup menu to a component using MouseListener to detect right-clicks.
*/

import javax.swing.*;
import java.awt.event.*;

public class PopupMenuExample {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Popup Menu Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a text area
        JTextArea textArea = new JTextArea();
        frame.add(textArea);

        // Create popup menu
        JPopupMenu popupMenu = new JPopupMenu();

        // Create menu items
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        // Add action listeners
        cut.addActionListener(e -> textArea.cut());
        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());

        // Add items to popup menu
        popupMenu.add(cut);
        popupMenu.add(copy);
        popupMenu.add(paste);

        // Add mouse listener to show popup menu on right-click
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) { // For Windows/Linux
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) { // For macOS
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        frame.setVisible(true);
    }
}
