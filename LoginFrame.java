import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel title, userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton, clearButton, backButton;

    LoginFrame() {
        setTitle("Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading
        title = new JLabel(
                "HOSPITAL QUEUE MANAGEMENT SYSTEM",
                JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBackground(new Color(25, 55, 100));
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 10, 25, 10));

        add(title, BorderLayout.NORTH);

        // Login Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        userField = new JTextField(15);
        passField = new JPasswordField(15);

        userField.setFont(new Font("Arial", Font.PLAIN, 16));
        passField.setFont(new Font("Arial", Font.PLAIN, 16));

        userField.setPreferredSize(new Dimension(230, 38));
        passField.setPreferredSize(new Dimension(230, 38));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 25, 25));

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        JButton[] buttons = {
            loginButton, clearButton, backButton
        };

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 17));
            button.setPreferredSize(new Dimension(130, 45));
            button.setFocusPainted(false);
        }

        loginButton.setBackground(new Color(25, 100, 180));
        loginButton.setForeground(Color.WHITE);

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);

        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {

            String username = userField.getText();
            String password =
                    new String(passField.getPassword());

            if (username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        this, "Login Successful!");

                new PatientRegistrationFrame();
                dispose();

            } else {
                JOptionPane.showMessageDialog(
                        this, "Invalid Username or Password!");
            }
        }

        if (e.getSource() == clearButton) {
            userField.setText("");
            passField.setText("");
        }

        if (e.getSource() == backButton) {
            new HomeFrame();
            dispose();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
