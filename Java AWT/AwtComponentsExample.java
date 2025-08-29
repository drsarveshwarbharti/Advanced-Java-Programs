//Java AWT program that creates a window with all the basic components (Label, TextField, Button, Checkbox, TextArea, Choice, List, etc.).
//By Extending Frame Class

import java.awt.*;
import java.awt.event.*;

public class AwtComponentsExample extends Frame {
    
    AWTComponentsExample() {
        // Set Frame properties
        setTitle("AWT Components Example");
        setSize(500, 500);
        setLayout(new FlowLayout()); // Layout manager
        setVisible(true);

        // Label
        Label label = new Label("Enter your name:");
        add(label);

        // TextField
        TextField textField = new TextField(20);
        add(textField);

        // Button
        Button button = new Button("Click Me");
        add(button);

        // Checkbox
        Checkbox checkbox1 = new Checkbox("Java");
        Checkbox checkbox2 = new Checkbox("Python");
        add(checkbox1);
        add(checkbox2);

        // CheckboxGroup (Radio Buttons)
        CheckboxGroup genderGroup = new CheckboxGroup();
        Checkbox male = new Checkbox("Male", genderGroup, false);
        Checkbox female = new Checkbox("Female", genderGroup, false);
        add(male);
        add(female);

        // TextArea
        TextArea textArea = new TextArea("Enter your address here...", 5, 30);
        add(textArea);

        // Choice (Drop-down menu)
        Choice choice = new Choice();
        choice.add("C");
        choice.add("C++");
        choice.add("Java");
        choice.add("Python");
        choice.add("JavaScript");
        add(choice);

        // List (Multiple selection)
        List list = new List(4, true);
        list.add("Apple");
        list.add("Banana");
        list.add("Mango");
        list.add("Orange");
        add(list);

        // Event handling for Button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello, " + textField.getText() + "!");
            }
        });

        // Window Closing Event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new AWTComponentsExample();
    }
}
