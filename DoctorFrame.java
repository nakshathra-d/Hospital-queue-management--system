
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoctorFrame extends JFrame
        implements ActionListener {

    JLabel title, departmentLabel, instructionLabel;
    JRadioButton doctor1Button, doctor2Button, doctor3Button;
    ButtonGroup doctorGroup;
    JButton backButton, continueButton;

    String department;
    String selectedDoctor = "";

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    DoctorFrame(String dept, String oldDoctor) {

        department = dept;

        setTitle("Doctor Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(lightBlue);

        // Heading
        title = new JLabel("SELECT DOCTOR", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(white);
        title.setBackground(navyBlue);
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 15, 25, 15));

        add(title, BorderLayout.NORTH);

        // Doctor selection panel
        JPanel doctorPanel = new JPanel(
                new GridLayout(5, 1, 10, 15));
        doctorPanel.setBackground(white);
        doctorPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 220, 245), 2),
                        BorderFactory.createEmptyBorder(
                                35, 60, 35, 60)));

        departmentLabel = new JLabel("Department: " + department);
        departmentLabel.setFont(
                new Font("Arial", Font.BOLD, 20));
        departmentLabel.setForeground(blue);

        instructionLabel = new JLabel("Choose a doctor:");
        instructionLabel.setFont(
                new Font("Arial", Font.BOLD, 18));
        instructionLabel.setForeground(blue);

        // Existing doctors based on department
        String[] doctors;

        switch (department) {
            case "General Medicine":
                doctors = new String[]{
                    "Dr. Arun Kumar",
                    "Dr. Meera Nair",
                    "Dr. Rahul Das"
                };
                break;

            case "Cardiology":
                doctors = new String[]{
                    "Dr. Anil Menon",
                    "Dr. Lakshmi Raj",
                    "Dr. Vivek Thomas"
                };
                break;

            case "ENT":
                doctors = new String[]{
                    "Dr. Suresh Kumar",
                    "Dr. Nisha Varma",
                    "Dr. Ajay Mohan"
                };
                break;

            case "Pediatrics":
                doctors = new String[]{
                    "Dr. Priya Nair",
                    "Dr. Kiran Joseph",
                    "Dr. Sneha Das"
                };
                break;

            default:
                doctors = new String[]{
                    "Dr. Arun Kumar",
                    "Dr. Meera Nair",
                    "Dr. Rahul Das"
                };
        }

        doctor1Button = new JRadioButton(doctors[0]);
        doctor2Button = new JRadioButton(doctors[1]);
        doctor3Button = new JRadioButton(doctors[2]);

        JRadioButton[] doctorButtons = {
            doctor1Button, doctor2Button, doctor3Button
        };

        doctorGroup = new ButtonGroup();

        for (JRadioButton button : doctorButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.setPreferredSize(new Dimension(400, 55));
            button.setForeground(textBlue);
            button.setBackground(white);
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setBorder(
                    BorderFactory.createEmptyBorder(5, 10, 5, 10));

            doctorGroup.add(button);
            doctorPanel.add(button);
        }

        // Restore previous doctor selection
        if (oldDoctor != null) {
            for (JRadioButton button : doctorButtons) {
                if (button.getText().equals(oldDoctor)) {
                    button.setSelected(true);
                }
            }
        }

        doctorPanel.add(departmentLabel, 0);
        doctorPanel.add(instructionLabel, 1);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(lightBlue);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35));
        centerPanel.add(doctorPanel);

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

            if (doctor1Button.isSelected()) {
                selectedDoctor = doctor1Button.getText();
            } else if (doctor2Button.isSelected()) {
                selectedDoctor = doctor2Button.getText();
            } else if (doctor3Button.isSelected()) {
                selectedDoctor = doctor3Button.getText();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Please select a doctor!");
                return;
            }

            dispose();

            new PrioritySelectionFrame(
                    department,
                    selectedDoctor,
                    "",
                    "",
                    "");
        }

        else if (e.getSource() == backButton) {

            dispose();
            new DepartmentFrame(department);
        }
    }
}
