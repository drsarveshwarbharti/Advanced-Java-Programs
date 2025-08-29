//Swing-based version that creates a window (JFrame) with all the major Swing components (JLabel, JTextField, JButton, JCheckBox, JRadioButton, JTextArea, JComboBox, JList, JMenuBar, etc.)

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingAllComponents {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Swing Components Example");
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel
        JLabel label = new JLabel("Enter your name:");
        frame.add(label);

        // JTextField
        JTextField textField = new JTextField(20);
        frame.add(textField);

        // JButton
        JButton button = new JButton("Click Me");
        frame.add(button);

        // JCheckBox
        JCheckBox cb1 = new JCheckBox("Java");
        JCheckBox cb2 = new JCheckBox("Python");
        frame.add(cb1);
        frame.add(cb2);

        // JRadioButton (with ButtonGroup)
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        frame.add(male);
        frame.add(female);

        // JTextArea
        JTextArea textArea = new JTextArea("Enter your address...", 5, 30);
        frame.add(new JScrollPane(textArea)); // with scrollbar

        // JComboBox (Drop-down menu)
        String languages[] = {"C", "C++", "Java", "Python", "JavaScript"};
        JComboBox<String> comboBox = new JComboBox<>(languages);
        frame.add(comboBox);

        // JList (Multiple selection)
        String fruits[] = {"Apple", "Banana", "Mango", "Orange"};
        JList<String> list = new JList<>(fruits);
        list.setVisibleRowCount(4);
        frame.add(new JScrollPane(list));

        // JMenuBar with Menu and MenuItems
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        frame.setJMenuBar(menuBar);

        // Button ActionListener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello, " + textField.getText() + "!");
            }
        });

        // Exit menu action
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
}
