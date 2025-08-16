//Combining Event Handling + Painting to make interactive drawings using mouse

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseDrawingApp extends JPanel {
    private int x = -1, y = -1;

    public DrawingApp() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint(); // triggers paintComponent
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (x >= 0 && y >= 0) {
            g.setColor(Color.MAGENTA);
            g.fillOval(x - 10, y - 10, 20, 20); // draw circle at click point
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mouse Drawing");
        MouseDrawingApp panel = new MouseDrawingApp();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
