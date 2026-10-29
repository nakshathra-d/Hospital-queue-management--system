import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame implements ActionListener {

    JLabel titleLabel, subtitleLabel, welcomeLabel;
    JButton loginButton, exitButton;
    JPanel headerPanel, centerPanel, buttonPanel;

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    HomeFrame() {

        setTitle("Hospital Queue Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        getContentPane().setBackground(lightBlue);

        // Header Panel
        headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.setBackground(navyBlue);
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(22, 15, 22, 15));

        titleLabel = new JLabel(
                "Hospital Queue Management System",
                JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setForeground(white);

        subtitleLabel = new JLabel("Home", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        subtitleLabel.setForeground(white);

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // Center Panel
        centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(lightBlue);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(35, 20, 35, 20));

        welcomeLabel = new JLabel(
                "Welcome to Our Hospital",
                JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(navyBlue);

        JLabel infoLabel = new JLabel(
                "Your health and care are our priority.",
                JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 21));
        infoLabel.setForeground(textBlue);

        JLabel instructionLabel = new JLabel(
                "Click Login to continue",
                JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        instructionLabel.setForeground(textBlue);

        centerPanel.add(welcomeLabel);
        centerPanel.add(infoLabel);
        centerPanel.add(instructionLabel);

        // Button Panel
        buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 25, 22));
        buttonPanel.setBackground(lightBlue);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 15, 10));

        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        styleButton(loginButton, blue, white, 125, 48);
        styleButton(exitButton, navyBlue, white, 110, 48);

        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Display frame
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleButton(JButton button, Color background,
                             Color foreground, int width, int height) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            new LoginFrame();
            dispose();
        }

        if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new HomeFrame();
    }
}
