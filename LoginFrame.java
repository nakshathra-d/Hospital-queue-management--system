import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel title, userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton, clearButton, backButton;

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    LoginFrame() {
        setTitle("Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(lightBlue);

        // Heading
        title = new JLabel(
                "HOSPITAL QUEUE MANAGEMENT SYSTEM",
                JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(white);
        title.setBackground(navyBlue);
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 15, 25, 15));

        add(title, BorderLayout.NORTH);

        // Login Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 220, 245), 2),
                BorderFactory.createEmptyBorder(35, 45, 35, 45)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(18, 18, 18, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        Font labelFont = new Font("Arial", Font.BOLD, 21);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        userLabel.setFont(labelFont);
        passLabel.setFont(labelFont);
        userLabel.setForeground(blue);
        passLabel.setForeground(blue);

        userField = new JTextField(15);
        passField = new JPasswordField(15);

        userField.setFont(fieldFont);
        passField.setFont(fieldFont);

        userField.setPreferredSize(new Dimension(300, 45));
        passField.setPreferredSize(new Dimension(300, 45));

        userField.setForeground(textBlue);
        passField.setForeground(textBlue);

        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(160, 190, 225), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(160, 190, 225), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(passField, gbc);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(lightBlue);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));
        centerPanel.add(formPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 22, 20));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        styleButton(loginButton, blue, white, 130, 48);
        styleButton(clearButton, white, navyBlue, 115, 48);
        styleButton(backButton, white, navyBlue, 110, 48);

        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);

        // Display frame
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleButton(JButton button, Color background,
                             Color foreground, int width, int height) {
        button.setFont(new Font("Arial", Font.BOLD, 19));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    @Override
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
