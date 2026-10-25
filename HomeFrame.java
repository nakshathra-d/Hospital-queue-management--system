import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeFrame extends JFrame implements ActionListener {

    JLabel titleLabel, welcomeLabel;
    JLabel descriptionLabel1, descriptionLabel2;
    JLabel footerLabel;

    JButton loginButton, exitButton;

    HomeFrame() {

        // Frame Settings
        setTitle("Hospital Queue Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(900, 100));
        headerPanel.setBackground(new Color(25, 75, 130));

        titleLabel = new JLabel(
            "Hospital Queue Management System",
            JLabel.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(
            contentPanel, BoxLayout.Y_AXIS
        ));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(
            BorderFactory.createEmptyBorder(80, 40, 50, 40)
        );

        // Welcome Heading
        welcomeLabel = new JLabel(
            "Welcome to Our Hospital",
            JLabel.CENTER
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(25, 75, 130));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Description - No HTML Used
        descriptionLabel1 = new JLabel(
            "Manage patient registration and doctor selection.",
            JLabel.CENTER
        );

        descriptionLabel2 = new JLabel(
            "Generate tokens and check queue status easily.",
            JLabel.CENTER
        );

        descriptionLabel1.setFont(
            new Font("Arial", Font.PLAIN, 17)
        );

        descriptionLabel2.setFont(
            new Font("Arial", Font.PLAIN, 17)
        );

        descriptionLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(160, 45));
        loginButton.setMaximumSize(new Dimension(160, 45));
        loginButton.setBackground(new Color(25, 100, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.setMaximumSize(new Dimension(160, 40));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Components to Content Panel
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createVerticalStrut(25));

        contentPanel.add(descriptionLabel1);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(descriptionLabel2);

        contentPanel.add(Box.createVerticalStrut(35));
        contentPanel.add(loginButton);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(exitButton);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(235, 242, 250));

        footerLabel = new JLabel(
            "Hospital Queue Management System"
        );

        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(footerLabel);

        // Event Handling
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add Panels to Frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Display Frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == loginButton) {

                new LoginFrame();
                dispose();

            } else if (e.getSource() == exitButton) {

                int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
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
        SwingUtilities.invokeLater(() -> new HomeFrame());
    }
}
