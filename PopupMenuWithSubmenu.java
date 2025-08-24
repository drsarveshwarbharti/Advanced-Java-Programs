//Interactive popup menu with submenus attached to a JButton and a JPanel.
/*
What’s new here:
Submenu (JMenu) inside a popup menu for color selection.
Two different popup menus:
    One for a JButton.
    One for a JPanel.
Interactive actions:
  Show a message dialog.
  Change button background color.
 */ 
import javax.swing.*;
import java.awt.event.*;

public class PopupMenuWithSubmenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Popup Menu with Submenu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Using absolute positioning for simplicity

        // Button to attach popup menu
        JButton button = new JButton("Right-click me");
        button.setBounds(50, 50, 150, 40);
        frame.add(button);

        // Panel to attach another popup menu
        JPanel panel = new JPanel();
        panel.setBounds(50, 150, 300, 100);
        panel.setBorder(BorderFactory.createTitledBorder("Right-click inside this panel"));
        frame.add(panel);

        // Create popup menu for button
        JPopupMenu buttonMenu = new JPopupMenu();
        JMenuItem helloItem = new JMenuItem("Say Hello");
        helloItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hello!"));
        buttonMenu.add(helloItem);

        // Add submenu
        JMenu colorsMenu = new JMenu("Choose Color");
        JMenuItem redItem = new JMenuItem("Red");
        JMenuItem blueItem = new JMenuItem("Blue");
        colorsMenu.add(redItem);
        colorsMenu.add(blueItem);

        // Action listeners for submenu
        redItem.addActionListener(e -> button.setBackground(java.awt.Color.RED));
        blueItem.addActionListener(e -> button.setBackground(java.awt.Color.BLUE));

        buttonMenu.add(colorsMenu);

        // Mouse listener for button
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) buttonMenu.show(e.getComponent(), e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) buttonMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        // Create popup menu for panel
        JPopupMenu panelMenu = new JPopupMenu();
        JMenuItem infoItem = new JMenuItem("Show Info");
        infoItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This is a panel popup menu"));
        panelMenu.add(infoItem);

        // Mouse listener for panel
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) panelMenu.show(e.getComponent(), e.getX(), e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) panelMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        frame.setVisible(true);
    }
}
