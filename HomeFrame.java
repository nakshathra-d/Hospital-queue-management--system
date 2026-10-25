import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame implements ActionListener {

    JLabel titleLabel, subtitleLabel, welcomeLabel;
    JButton loginButton, exitButton;
    JPanel headerPanel, centerPanel, buttonPanel;

    HomeFrame() {

        setTitle("Hospital Queue Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1, 5, 5));
        headerPanel.setBackground(new Color(25, 75, 130));

        titleLabel = new JLabel("Hospital Queue Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);

        subtitleLabel = new JLabel(
                "Home",
                JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        welcomeLabel = new JLabel(
                "Welcome to Our Hospital",
                JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel infoLabel = new JLabel(
                "Your health and care are our priority.",
                JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 17));

        JLabel instructionLabel = new JLabel(
                "Click Login to continue",
                JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        centerPanel.add(welcomeLabel);
        centerPanel.add(infoLabel);
        centerPanel.add(instructionLabel);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(
                FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(Color.WHITE);

        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));

        loginButton.setPreferredSize(new Dimension(150, 45));
        exitButton.setPreferredSize(new Dimension(150, 45));

        loginButton.setBackground(new Color(25, 100, 180));
        loginButton.setForeground(Color.WHITE);

        exitButton.setBackground(new Color(180, 50, 50));
        exitButton.setForeground(Color.WHITE);

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
