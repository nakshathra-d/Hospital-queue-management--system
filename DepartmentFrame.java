
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JButton generalButton, cardioButton, entButton, pediatricButton;
    JButton backButton;

    Color navy = new Color(25, 55, 100);
    Color lightBlue = new Color(230, 240, 250);
    Color background = new Color(245, 248, 252);

    public DepartmentFrame() {

        setTitle("Hospital - Department Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 15));
        getContentPane().setBackground(background);

        // Heading
        titleLabel = new JLabel(
            "SELECT DEPARTMENT", JLabel.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(navy);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(
            BorderFactory.createEmptyBorder(25, 10, 25, 10)
        );

        add(titleLabel, BorderLayout.NORTH);

        // Department buttons
        JPanel centerPanel = new JPanel(
            new GridLayout(2, 2, 20, 20)
        );
        centerPanel.setBackground(background);

        // Increased outer margins make the buttons narrower
        centerPanel.setBorder(
            BorderFactory.createEmptyBorder(60, 180, 50, 180)
        );

        generalButton = new JButton("General Medicine");
        cardioButton = new JButton("Cardiology");
        entButton = new JButton("ENT");
        pediatricButton = new JButton("Pediatrics");

        JButton[] departments = {
            generalButton, cardioButton, entButton, pediatricButton
        };

        for (JButton button : departments) {
            button.setFont(new Font("Arial", Font.BOLD, 19));
            button.setBackground(lightBlue);
            button.setForeground(navy);
            button.setFocusPainted(false);
            button.setBorder(
                BorderFactory.createLineBorder(
                    new Color(190, 210, 230), 2
                )
            );
            button.addActionListener(this);
            centerPanel.add(button);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 20, 15)
        );
        bottomPanel.setBackground(background);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(140, 42));
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {
            dispose();
            new PatientRegistrationFrame();

        } else {
            JButton selectedButton = (JButton) e.getSource();
            String department = selectedButton.getText();

            setVisible(false);
            new DoctorFrame(department, this);
        }
    }

    public static void main(String[] args) {
        new DepartmentFrame();
    }
}
