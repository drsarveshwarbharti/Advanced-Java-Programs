//Java AWT program where we directly create a Frame object (instead of extending Frame)

import java.awt.*;
import java.awt.event.*;

public class AwtAllComponents {
    public static void main(String[] args) {
        // Create Frame object
        Frame frame = new Frame("AWT Components Example");
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        // Label
        Label label = new Label("Enter your name:");
        frame.add(label);

        // TextField
        TextField textField = new TextField(20);
        frame.add(textField);

        // Button
        Button button = new Button("Click Me");
        frame.add(button);

        // Checkbox
        Checkbox cb1 = new Checkbox("Java");
        Checkbox cb2 = new Checkbox("Python");
        frame.add(cb1);
        frame.add(cb2);

        // Radio Buttons using CheckboxGroup
        CheckboxGroup genderGroup = new CheckboxGroup();
        Checkbox male = new Checkbox("Male", genderGroup, false);
        Checkbox female = new Checkbox("Female", genderGroup, false);
        frame.add(male);
        frame.add(female);

        // TextArea
        TextArea textArea = new TextArea("Enter your address...", 5, 30);
        frame.add(textArea);

        // Choice (Drop-down)
        Choice choice = new Choice();
        choice.add("C");
        choice.add("C++");
        choice.add("Java");
        choice.add("Python");
        frame.add(choice);

        // List
        List list = new List(4, true);
        list.add("Apple");
        list.add("Banana");
        list.add("Mango");
        list.add("Orange");
        frame.add(list);

        // Button Action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello, " + textField.getText() + "!");
            }
        });

        // Window Closing
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });
    }
}
