//Swing Popup Menu (JPopupMenu)

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopupMenuExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Popup Menu Example");
        JTextArea textArea = new JTextArea("Right-click inside this area to see popup menu");
        textArea.setBounds(20, 20, 300, 200);

        // Create Popup Menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        // Add items to popup
        popupMenu.add(cutItem);
        popupMenu.add(copyItem);
        popupMenu.add(pasteItem);

        // Add Mouse Listener for popup
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { showPopup(e); }
            public void mouseReleased(MouseEvent e) { showPopup(e); }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) { // right-click
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        frame.add(textArea);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
