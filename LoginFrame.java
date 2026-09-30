import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel titleLabel, usernameLabel, passwordLabel;
    JTextField usernameField, passwordField;
    JButton loginButton, clearButton;

    LoginFrame() {

        // Frame
        setTitle("Hospital Queue Management System");
        setSize(450, 300);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Hospital Queue Management System");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        // Text Fields
        usernameField = new JTextField();
        passwordField = new JTextField();

        // Buttons
        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(usernameLabel);
        add(usernameField);

        add(passwordLabel);
        add(passwordField);

        add(loginButton);
        add(clearButton);

        // Event Handling
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Display frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == clearButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}