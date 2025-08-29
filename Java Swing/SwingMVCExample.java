/* Swing program based on the MVC (Model–View–Controller) pattern.
Simple Login Example with MVC architecture, since it’s small but clearly demonstrates the concept.
MVC Structure
1. Model → Handles the data & logic (here: validating username/password).
2. View → GUI components (JFrame, JTextField, JButton, etc.).
3 Controller → Connects View and Model, handles events, and updates View. */


// ===== Model =====
class LoginModel {
    private String correctUser = "admin";
    private String correctPass = "1234";

    public boolean validate(String user, String pass) {
        return user.equals(correctUser) && pass.equals(correctPass);
    }
}

// ===== View =====
import javax.swing.*; //Add this statement at the top of this program
import java.awt.*; //Add this statement at the top of this program

class LoginView extends JFrame {
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;
    JLabel messageLabel;

    public LoginView() {
        setTitle("MVC Login Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Username
        userField = new JTextField();
        add(new JLabel("Username:"));
        add(userField);

        // Password
        passField = new JPasswordField();
        add(new JLabel("Password:"));
        add(passField);

        // Login Button
        loginButton = new JButton("Login");
        add(loginButton);

        // Message
        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel);

        setVisible(true);
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }

    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }
}

// ===== Controller =====
import java.awt.event.*; //Add this statement at the top of this program

class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // Handle button click
        view.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = view.getUsername();
                String pass = view.getPassword();

                if (model.validate(user, pass)) {
                    view.setMessage("Login Successful!");
                } else {
                    view.setMessage("Invalid Credentials!");
                }
            }
        });
    }
}

// ===== Main =====
public class SwingMVCExample {
    public static void main(String[] args) {
        LoginModel model = new LoginModel();
        LoginView view = new LoginView();
        new LoginController(model, view);
    }
}
