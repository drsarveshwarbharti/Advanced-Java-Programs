/* Write a Java Swing program with a label, text field, button, checkboxes, radio buttons, combo box, and a text area. 
On clicking the button, display the entered name, selected gender, chosen hobbies, and city in a label at the bottom. */
//This program demonstrates JLabel, JTextField, JButton, JCheckBox, JRadioButton, JComboBox, JTextArea, and event handling

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingBasicsExercise {
    public static void main(String[] args) {
        // Frame setup
        JFrame frame = new JFrame("Swing Basics Exercise");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Label and TextField
        JLabel nameLabel = new JLabel("Enter Your Name:");
        JTextField nameField = new JTextField(15);

        // CheckBoxes
        JCheckBox hobby1 = new JCheckBox("Reading");
        JCheckBox hobby2 = new JCheckBox("Sports");

        // RadioButtons with ButtonGroup
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // ComboBox
        String cities[] = {"Delhi", "Mumbai", "Chennai"};
        JComboBox<String> cityBox = new JComboBox<>(cities);

        // TextArea
        JTextArea comments = new JTextArea(3, 20);
        comments.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Submit Button
        JButton submitButton = new JButton("Submit");

        // Result Label
        JLabel resultLabel = new JLabel("Your details will appear here.");

        // Button Action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                // Gender
                String gender = "";
                if (male.isSelected()) gender = "Male";
                else if (female.isSelected()) gender = "Female";

                // Hobbies
                String hobbies = "";
                if (hobby1.isSelected()) hobbies += "Reading ";
                if (hobby2.isSelected()) hobbies += "Sports ";

                // City
                String city = cityBox.getSelectedItem().toString();

                // Comments
                String feedback = comments.getText();

                // Display all in resultLabel
                resultLabel.setText("<html>Name: " + name +
                        "<br>Gender: " + gender +
                        "<br>Hobbies: " + hobbies +
                        "<br>City: " + city +
                        "<br>Comments: " + feedback + "</html>");
            }
        });

        // Add components to frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(hobby1);
        frame.add(hobby2);
        frame.add(male);
        frame.add(female);
        frame.add(cityBox);
        frame.add(new JLabel("Comments:"));
        frame.add(comments);
        frame.add(submitButton);
        frame.add(resultLabel);

        // Make visible
        frame.setVisible(true);
    }
}
