//Swing Event Handling - Multiple Listeners

import javax.swing.*;
import java.awt.event.*;

public class MultipleListenerEvent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Multiple Event Example");
        JTextField textField = new JTextField();
        JButton button = new JButton("Submit");

        textField.setBounds(50, 50, 200, 30);
        button.setBounds(50, 100, 100, 30);

        // ActionListener for button
        button.addActionListener(e -> {
            String text = textField.getText();
            JOptionPane.showMessageDialog(frame, "You typed: " + text);
        });

        // KeyListener for text field
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JOptionPane.showMessageDialog(frame, "Enter pressed: " + textField.getText());
                }
            }
        });

        frame.add(textField);
        frame.add(button);
        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
