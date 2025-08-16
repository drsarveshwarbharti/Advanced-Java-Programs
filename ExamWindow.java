/*Java Swing program that simulates an Exam/Test Window with:
1. Few questions (Multiple Choice + Text-based)
2. A Submit button
3. Logic to calculate and display marks*/

//2 Multiple Choice Questions (radio buttons)
//1 Text-based Question (JTextField)
//Submit Button calculates score
//Displays marks out of 3


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExamWindow {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Online Exam Window");
        frame.setSize(600, 500);
        frame.setLayout(new GridLayout(0, 1, 10, 10)); // vertical layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Question 1 (Multiple Choice)
        JLabel q1 = new JLabel("Q1. Which language is platform independent?");
        JRadioButton q1a = new JRadioButton("C");
        JRadioButton q1b = new JRadioButton("C++");
        JRadioButton q1c = new JRadioButton("Java");
        JRadioButton q1d = new JRadioButton("Python");
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(q1a); bg1.add(q1b); bg1.add(q1c); bg1.add(q1d);

        // Question 2 (Multiple Choice)
        JLabel q2 = new JLabel("Q2. Which company developed Windows OS?");
        JRadioButton q2a = new JRadioButton("Google");
        JRadioButton q2b = new JRadioButton("Microsoft");
        JRadioButton q2c = new JRadioButton("Apple");
        JRadioButton q2d = new JRadioButton("IBM");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(q2a); bg2.add(q2b); bg2.add(q2c); bg2.add(q2d);

        // Question 3 (Text Answer)
        JLabel q3 = new JLabel("Q3. What is 5 + 7 ?");
        JTextField answer3 = new JTextField(10);

        // Submit Button
        JButton submit = new JButton("Submit Exam");

        // Result Label
        JLabel result = new JLabel("Your Score: ");
        result.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to Frame
        frame.add(q1); frame.add(q1a); frame.add(q1b); frame.add(q1c); frame.add(q1d);
        frame.add(q2); frame.add(q2a); frame.add(q2b); frame.add(q2c); frame.add(q2d);
        frame.add(q3); frame.add(answer3);
        frame.add(submit);
        frame.add(result);

        // Logic for Submit Button
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int score = 0;

                // Q1: Correct Answer = Java
                if (q1c.isSelected()) score++;

                // Q2: Correct Answer = Microsoft
                if (q2b.isSelected()) score++;

                // Q3: Correct Answer = 12
                if (answer3.getText().trim().equals("12")) score++;

                result.setText("Your Score: " + score + "/3");
            }
        });

        // Show Frame
        frame.setVisible(true);
    }
}
