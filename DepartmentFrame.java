import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentFrame extends JFrame
        implements ActionListener {

    JLabel title;

    JButton generalButton;
    JButton cardioButton;
    JButton entButton;
    JButton pediatricButton;
    JButton backButton;

    public DepartmentFrame() {

        setTitle("Department Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading
        title = new JLabel(
                "DEPARTMENT SELECTION",
                JLabel.CENTER);

        title.setFont(
                new Font("Arial", Font.BOLD, 24));

        title.setForeground(Color.WHITE);
        title.setBackground(new Color(25, 55, 100));
        title.setOpaque(true);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 25, 10));

        add(title, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(
                new GridLayout(2, 2, 25, 25));

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        50, 100, 50, 100));

        // Department buttons
        generalButton = new JButton("General Medicine");
        cardioButton = new JButton("Cardiology");
        entButton = new JButton("ENT");
        pediatricButton = new JButton("Pediatrics");

        JButton[] departmentButtons = {
            generalButton,
            cardioButton,
            entButton,
            pediatricButton
        };

        for (JButton button : departmentButtons) {

            button.setFont(
                    new Font("Arial", Font.BOLD, 20));

            button.setBackground(
                    new Color(230, 240, 250));

            button.setFocusPainted(false);
            button.addActionListener(this);

            centerPanel.add(button);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER, 25, 20));

        backButton = new JButton("Back");

        backButton.setFont(
                new Font("Arial", Font.BOLD, 17));

        backButton.setPreferredSize(
                new Dimension(140, 45));

        backButton.setFocusPainted(false);

        // Back button action
        backButton.addActionListener(this);

        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Window settings
        setSize(900, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Back to Patient Registration
        if (e.getSource() == backButton) {

            dispose();

            PatientRegistrationFrame patientFrame =
                    new PatientRegistrationFrame();

            patientFrame.setVisible(true);
        }

        // Department selection
        else {

            JButton selectedButton =
                    (JButton) e.getSource();

            String department =
                    selectedButton.getText();

            JOptionPane.showMessageDialog(
                    this,
                    department + " selected successfully!");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DepartmentFrame();
        });
    }
        }
