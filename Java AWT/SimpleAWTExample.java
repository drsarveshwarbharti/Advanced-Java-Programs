//Java AWT program that creates a window with a label and a button
/*
How it works:
Shows a label and a button.
When the button is clicked, the label text updates.
Closes properly when you click the window close button.
*/


import java.awt.*;
import java.awt.event.*;

public class SimpleAWTExample {
    public static void main(String[] args) {
        Frame frame = new Frame("AWT Example");

        // Create a label
        Label label = new Label("This is a label.");
        label.setBounds(100, 70, 200, 30);
        
        // Create a button
        Button button = new Button("Click Me");
        button.setBounds(100, 120, 80, 30);

        // Optional: Set action for button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Button clicked!");
            }
        });

        // Add components to frame
        frame.add(label);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setVisible(true);

        // Close window event
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }
}
