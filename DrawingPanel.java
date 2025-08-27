/*
Create a Swing application with a main window (JFrame).
Inside it, add a custom panel (JPanel) where you override paintComponent(Graphics g).
In the custom panel:
  Draw at least three different shapes (rectangle, oval, line).
  Draw a string of text in a custom color and font.
  (Optional challenge) Draw an image from your computer inside the panel.
Add a button below the panel. When clicked, it should:
Change the color of shapes randomly.
Call repaint() to update the drawing.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PaintingExercise extends JFrame {
    private DrawingPanel drawingPanel;

    public PaintingExercise() {
        setTitle("Swing Painting Exercise");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JButton btnChangeColor = new JButton("Change Colors");
        btnChangeColor.addActionListener(e -> {
            drawingPanel.changeColors();
            drawingPanel.repaint();
        });
        add(btnChangeColor, BorderLayout.SOUTH);
    }

    class DrawingPanel extends JPanel {
        private Color rectColor = Color.BLUE;
        private Color ovalColor = Color.RED;
        private Color textColor = Color.MAGENTA;
        private Random random = new Random();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw rectangle
            g2d.setColor(rectColor);
            g2d.fillRect(50, 50, 100, 80);

            // Draw oval
            g2d.setColor(ovalColor);
            g2d.fillOval(200, 50, 120, 80);

            // Draw line
            g2d.setColor(Color.BLACK);
            g2d.drawLine(50, 200, 300, 200);

            // Draw text
            g2d.setColor(textColor);
            g2d.setFont(new Font("Serif", Font.BOLD, 20));
            g2d.drawString("Hello Swing Painting!", 50, 250);
        }

        public void changeColors() {
            rectColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            ovalColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            textColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintingExercise().setVisible(true));
    }
}
