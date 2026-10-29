import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentFrame extends JFrame
        implements ActionListener {

    JLabel title, instructionLabel;
    JRadioButton generalButton, cardioButton;
    JRadioButton entButton, pediatricsButton;
    JButton continueButton, backButton;

    ButtonGroup departmentGroup;
    String selectedDepartment = "";

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    DepartmentFrame(String oldDepartment) {

        setTitle("Department Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(lightBlue);

        // Heading
        title = new JLabel("SELECT DEPARTMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(white);
        title.setBackground(navyBlue);
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 15, 25, 15));

        add(title, BorderLayout.NORTH);

        // Department selection panel
        JPanel departmentPanel = new JPanel(
                new GridLayout(5, 1, 10, 15));
        departmentPanel.setBackground(white);
        departmentPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 220, 245), 2),
                        BorderFactory.createEmptyBorder(
                                35, 60, 35, 60)));

        instructionLabel = new JLabel("Choose a department:");
        instructionLabel.setFont(
                new Font("Arial", Font.BOLD, 20));
        instructionLabel.setForeground(blue);

        generalButton = new JRadioButton("General Medicine");
        cardioButton = new JRadioButton("Cardiology");
        entButton = new JRadioButton("ENT");
        pediatricsButton = new JRadioButton("Pediatrics");

        JRadioButton[] departments = {
            generalButton, cardioButton,
            entButton, pediatricsButton
        };

        departmentGroup = new ButtonGroup();

        for (JRadioButton button : departments) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(400, 55));
            button.setForeground(textBlue);
            button.setBackground(white);
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setBorder(
                    BorderFactory.createEmptyBorder(5, 10, 5, 10));

            departmentGroup.add(button);
            departmentPanel.add(button);
        }

        departmentPanel.add(instructionLabel, 0);

        // Restore previous selection
        if (oldDepartment != null) {
            if (oldDepartment.equals("General Medicine")) {
                generalButton.setSelected(true);
            } else if (oldDepartment.equals("Cardiology")) {
                cardioButton.setSelected(true);
            } else if (oldDepartment.equals("ENT")) {
                entButton.setSelected(true);
            } else if (oldDepartment.equals("Pediatrics")) {
                pediatricsButton.setSelected(true);
            }
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(lightBlue);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35));
        centerPanel.add(departmentPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Navigation buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 25, 20));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        backButton = new JButton("Back");
        continueButton = new JButton("Continue");

        styleButton(backButton, white, navyBlue, 110, 48);
        styleButton(continueButton, blue, white, 145, 48);

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);

        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(this);
        continueButton.addActionListener(this);

        setSize(900, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleButton(JButton button, Color background,
                             Color foreground, int width, int height) {
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(
                BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == continueButton) {

            if (generalButton.isSelected()) {
                selectedDepartment = "General Medicine";
            } else if (cardioButton.isSelected()) {
                selectedDepartment = "Cardiology";
            } else if (entButton.isSelected()) {
                selectedDepartment = "ENT";
            } else if (pediatricsButton.isSelected()) {
                selectedDepartment = "Pediatrics";
            } else {
                JOptionPane.showMessageDialog(
                        this, "Please select a department!");
                return;
            }

            dispose();
            new DoctorFrame(selectedDepartment, "");
        }

        else if (e.getSource() == backButton) {
            dispose();
            new LoginFrame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new DepartmentFrame(""));
    }
                }
