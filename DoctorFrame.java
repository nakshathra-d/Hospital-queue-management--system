
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoctorFrame extends JFrame implements ActionListener {

    JLabel titleLabel, subtitleLabel, selectedLabel;
    JButton doctor1Button, doctor2Button, doctor3Button;
    JButton backButton, continueButton;

    DepartmentFrame departmentFrame;
    String department;
    String selectedDoctor = "";

    Color navy = new Color(25, 55, 100);
    Color lightBlue = new Color(230, 240, 250);
    Color background = new Color(245, 248, 252);
    Color borderBlue = new Color(190, 210, 230);

    public DoctorFrame(String department,
                       DepartmentFrame departmentFrame) {

        this.department = department;
        this.departmentFrame = departmentFrame;

        setTitle("Hospital - Doctor Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 12));
        getContentPane().setBackground(background);

        // Heading
        titleLabel = new JLabel(
            "SELECT YOUR DOCTOR", JLabel.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 29));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(navy);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(
            BorderFactory.createEmptyBorder(24, 10, 24, 10)
        );

        add(titleLabel, BorderLayout.NORTH);

        // Main content
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setBackground(background);
        centerPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 100, 15, 100)
        );

        subtitleLabel = new JLabel(
            "Department: " + department, JLabel.CENTER
        );
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 21));
        subtitleLabel.setForeground(navy);
        subtitleLabel.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 10, 5)
        );

        centerPanel.add(subtitleLabel, BorderLayout.NORTH);

        // Compact doctor selection buttons
        JPanel doctorPanel = new JPanel(
            new GridLayout(3, 1, 15, 15)
        );
        doctorPanel.setBackground(background);
        doctorPanel.setBorder(
            BorderFactory.createEmptyBorder(5, 100, 5, 100)
        );

        doctor1Button = new JButton("Dr. Arun");
        doctor2Button = new JButton("Dr. Meera");
        doctor3Button = new JButton("Dr. Rahul");

        JButton[] doctors = {
            doctor1Button, doctor2Button, doctor3Button
        };

        for (JButton button : doctors) {
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setPreferredSize(new Dimension(300, 55));
            button.setBackground(lightBlue);
            button.setForeground(navy);
            button.setFocusPainted(false);
            button.setBorder(
                BorderFactory.createLineBorder(borderBlue, 2)
            );
            button.addActionListener(this);
            doctorPanel.add(button);
        }

        centerPanel.add(doctorPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom area
        JPanel bottomPanel = new JPanel(
            new GridLayout(2, 1, 5, 10)
        );
        bottomPanel.setBackground(background);
        bottomPanel.setBorder(
            BorderFactory.createEmptyBorder(5, 20, 18, 20)
        );

        selectedLabel = new JLabel(
            "Selected Doctor: None", JLabel.CENTER
        );
        selectedLabel.setFont(new Font("Arial", Font.BOLD, 19));
        selectedLabel.setForeground(navy);
        selectedLabel.setOpaque(true);
        selectedLabel.setBackground(lightBlue);
        selectedLabel.setBorder(
            BorderFactory.createLineBorder(borderBlue, 1)
        );

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 20, 5)
        );
        buttonPanel.setBackground(background);

        backButton = new JButton("Back");
        continueButton = new JButton("Continue");

        styleButton(backButton, lightBlue, navy);
        styleButton(continueButton, navy, Color.WHITE);

        backButton.addActionListener(this);
        continueButton.addActionListener(this);

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);

        bottomPanel.add(selectedLabel);
        bottomPanel.add(buttonPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(160, 44));
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {

            dispose();
            departmentFrame.setVisible(true);

        } else if (e.getSource() == doctor1Button) {

            selectedDoctor = "Dr. Arun";
            selectedLabel.setText(
                "Selected Doctor: " + selectedDoctor
            );

        } else if (e.getSource() == doctor2Button) {

            selectedDoctor = "Dr. Meera";
            selectedLabel.setText(
                "Selected Doctor: " + selectedDoctor
            );

        } else if (e.getSource() == doctor3Button) {

            selectedDoctor = "Dr. Rahul";
            selectedLabel.setText(
                "Selected Doctor: " + selectedDoctor
            );

        } else if (e.getSource() == continueButton) {

            if (selectedDoctor.equals("")) {

                JOptionPane.showMessageDialog(
                    this, "Please select a doctor first."
                );

            } else {

                setVisible(false);
                new TokenQueueFrame(
                    department, selectedDoctor, this
                );
            }
        }
    }
}
