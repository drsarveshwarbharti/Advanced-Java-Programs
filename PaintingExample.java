//Drawing graphics (shapes, text, images, etc.) in swing using paintComponent(Graphics g)

import javax.swing.*;
import java.awt.*;

public class PaintingExample extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clears background

        // Set color and draw shapes
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 60); // filled rectangle

        g.setColor(Color.BLUE);
        g.drawOval(200, 50, 100, 60); // oval outline

        g.setColor(Color.GREEN);
        g.fillOval(200, 150, 100, 60); // filled oval

        g.setColor(Color.BLACK);
        g.drawLine(50, 200, 300, 200); // line

        // Draw text
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Hello, Swing Painting!", 50, 250);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Painting in Swing");
        PaintingExample panel = new PaintingExample();

        frame.add(panel);
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
