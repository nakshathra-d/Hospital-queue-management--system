import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel titleLabel, usernameLabel, passwordLabel;
    JTextField usernameField, passwordField;
    JButton loginButton, clearButton;

    LoginFrame() {

        // Frame Settings
        setTitle("Hospital Queue Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // Title Board
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(900, 100));
        titlePanel.setBackground(new Color(25, 75, 130));

        titleLabel = new JLabel(
            "Hospital Queue Management System",
            JLabel.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(
            new GridLayout(2, 2, 15, 20)
        );

        formPanel.setBorder(
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );
        formPanel.setBackground(Color.WHITE);

        // Labels
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Text Fields
        usernameField = new JTextField();
        passwordField = new JTextField();

        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

        usernameField.setPreferredSize(new Dimension(250, 35));
        passwordField.setPreferredSize(new Dimension(250, 35));

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Center the form without stretching its fields
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        JPanel formBox = new JPanel(new BorderLayout(10, 10));
        formBox.setPreferredSize(new Dimension(600, 220));
        formBox.setBackground(Color.WHITE);

        formBox.add(formPanel, BorderLayout.CENTER);

        centerPanel.add(formBox);

        // Button Panel
        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 25, 20)
        );

        buttonPanel.setBackground(Color.WHITE);

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");

        loginButton.setPreferredSize(new Dimension(130, 40));
        clearButton.setPreferredSize(new Dimension(130, 40));

        loginButton.setBackground(new Color(25, 100, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        clearButton.setBackground(new Color(220, 230, 240));
        clearButton.setFocusPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);

        // Event Handling
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add Panels to Frame
        add(titlePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display Frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == loginButton) {

                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();

                if (username.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password."
                    );

                } else if (username.equals("admin")
                        && password.equals("1234")) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                    );

                    new PatientRegistrationFrame();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password."
                    );
                }
            }

            if (e.getSource() == clearButton) {

                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
