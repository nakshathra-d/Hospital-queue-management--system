import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PrioritySelectionFrame extends JFrame
        implements ActionListener {

    JLabel title, departmentLabel, doctorLabel;
    JLabel priorityLabel, proofLabel, timeLabel, fileLabel;

    JRadioButton seniorButton, disabilityButton;
    JRadioButton pregnantButton, normalButton;

    ButtonGroup priorityGroup;

    JButton uploadButton, backButton, continueButton;

    JComboBox<String> timeComboBox;

    String department;
    String doctor;
    String proofPath = "";

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    PrioritySelectionFrame(
            String department,
            String doctor,
            String selectedPriority,
            String selectedProof,
            String selectedTime) {

        this.department = department;
        this.doctor = doctor;
        this.proofPath = selectedProof == null ? "" : selectedProof;

        setTitle("Priority Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(white);

        // Heading
        title = new JLabel(
                "PRIORITY SELECTION", JLabel.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(white);
        title.setBackground(navyBlue);
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 15, 25, 15));

        add(title, BorderLayout.NORTH);

        // Main form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 50, 25, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(9, 15, 9, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        Font detailFont = new Font("Arial", Font.BOLD, 19);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font optionFont = new Font("Arial", Font.PLAIN, 19);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);

        departmentLabel = new JLabel("Department: " + department);
        doctorLabel = new JLabel("Selected Doctor: " + doctor);
        priorityLabel = new JLabel("Select Priority:");

        departmentLabel.setFont(detailFont);
        doctorLabel.setFont(detailFont);
        priorityLabel.setFont(labelFont);

        departmentLabel.setForeground(textBlue);
        doctorLabel.setForeground(textBlue);
        priorityLabel.setForeground(blue);

        seniorButton = new JRadioButton("Senior Citizen");
        disabilityButton = new JRadioButton(
                "Person with Disability");
        pregnantButton = new JRadioButton("Pregnant Woman");
        normalButton = new JRadioButton(
                "Normal / No Special Priority");

        JRadioButton[] priorityButtons = {
            seniorButton, disabilityButton,
            pregnantButton, normalButton
        };

        priorityGroup = new ButtonGroup();

        for (JRadioButton button : priorityButtons) {
            button.setFont(optionFont);
            button.setForeground(textBlue);
            button.setBackground(white);
            button.setFocusPainted(false);
            button.setOpaque(true);
            button.setBorder(
                    BorderFactory.createEmptyBorder(5, 8, 5, 8));
            priorityGroup.add(button);
        }

        // Restore previously selected priority
        if (selectedPriority != null) {
            if (selectedPriority.equals("Senior Citizen")) {
                seniorButton.setSelected(true);
            } else if (selectedPriority.equals(
                    "Person with Disability")
                    || selectedPriority.equals("Disability")) {
                disabilityButton.setSelected(true);
            } else if (selectedPriority.equals("Pregnant Woman")
                    || selectedPriority.equals("Pregnant")) {
                pregnantButton.setSelected(true);
            } else if (selectedPriority.equals(
                    "Normal / No Special Priority")
                    || selectedPriority.equals("Normal")) {
                normalButton.setSelected(true);
            }
        }

        // Proof selection
        proofLabel = new JLabel("Proof Document:");
        proofLabel.setFont(labelFont);
        proofLabel.setForeground(blue);

        uploadButton = new JButton("Upload Proof");
        uploadButton.setFont(new Font("Arial", Font.BOLD, 17));
        uploadButton.setPreferredSize(new Dimension(165, 42));
        uploadButton.setBackground(blue);
        uploadButton.setForeground(white);
        uploadButton.setFocusPainted(false);
        uploadButton.setBorderPainted(false);
        uploadButton.addActionListener(this);

        fileLabel = new JLabel(
                proofPath.isEmpty()
                ? "No file selected"
                : new File(proofPath).getName());

        fileLabel.setFont(fieldFont);
        fileLabel.setForeground(textBlue);

        // Preferred time
        timeLabel = new JLabel("Preferred Time:");
        timeLabel.setFont(labelFont);
        timeLabel.setForeground(blue);

        String[] timeSlots = {
            "-- Select Time --",
            "09:00 AM - 10:00 AM",
            "10:00 AM - 11:00 AM",
            "11:00 AM - 12:00 PM",
            "12:00 PM - 01:00 PM",
            "02:00 PM - 03:00 PM",
            "03:00 PM - 04:00 PM"
        };

        timeComboBox = new JComboBox<>(timeSlots);
        timeComboBox.setFont(fieldFont);
        timeComboBox.setPreferredSize(new Dimension(290, 44));
        timeComboBox.setBackground(white);
        timeComboBox.setForeground(textBlue);

        if (selectedTime != null) {
            timeComboBox.setSelectedItem(selectedTime);
        }

        // Add components
        int row = 0;

        addComponent(formPanel, departmentLabel,
                gbc, 0, row++, 2);

        addComponent(formPanel, doctorLabel,
                gbc, 0, row++, 2);

        addComponent(formPanel, priorityLabel,
                gbc, 0, row++, 2);

        for (JRadioButton button : priorityButtons) {
            addComponent(formPanel, button,
                    gbc, 0, row++, 2);
        }

        addComponent(formPanel, proofLabel,
                gbc, 0, row, 1);

        JPanel proofPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 0));
        proofPanel.setBackground(white);
        proofPanel.add(uploadButton);
        proofPanel.add(fileLabel);

        addComponent(formPanel, proofPanel,
                gbc, 1, row++, 1);

        addComponent(formPanel, timeLabel,
                gbc, 0, row, 1);

        addComponent(formPanel, timeComboBox,
                gbc, 1, row++, 1);

        // Keep the form centered and readable
        JPanel formWrapper = new JPanel(new GridBagLayout());
        formWrapper.setBackground(lightBlue);

        JPanel formCard = new JPanel(new BorderLayout());
        formCard.setBackground(white);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 220, 245), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        formCard.add(formPanel, BorderLayout.CENTER);

        formWrapper.add(formCard);
        add(formWrapper, BorderLayout.CENTER);

        // Navigation buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 30, 18));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        backButton = new JButton("Back");
        continueButton = new JButton("Continue");

        JButton[] buttons = {backButton, continueButton};

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 19));
            button.setPreferredSize(new Dimension(175, 50));
            button.setFocusPainted(false);
            button.setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.addActionListener(this);
        }

        backButton.setBackground(white);
        backButton.setForeground(navyBlue);

        continueButton.setBackground(blue);
        continueButton.setForeground(white);
        continueButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(white, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(850, 700));
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void addComponent(
            JPanel panel,
            Component component,
            GridBagConstraints gbc,
            int x, int y, int width) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;

        panel.add(component, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Upload proof
        if (e.getSource() == uploadButton) {

            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File selectedFile =
                        fileChooser.getSelectedFile();

                proofPath = selectedFile.getAbsolutePath();
                fileLabel.setText(selectedFile.getName());
            }
        }

        // Continue to token generation
        else if (e.getSource() == continueButton) {

            String selectedPriority = "";

            if (seniorButton.isSelected()) {
                selectedPriority = "Senior Citizen";
            } else if (disabilityButton.isSelected()) {
                selectedPriority = "Disability";
            } else if (pregnantButton.isSelected()) {
                selectedPriority = "Pregnant";
            } else if (normalButton.isSelected()) {
                selectedPriority = "Normal";
            }

            // Priority is mandatory
            if (selectedPriority.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a priority category!");
                return;
            }

            // Proof is mandatory for special categories
            if (!selectedPriority.equals("Normal")
                    && proofPath.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please upload proof for the selected priority!");
                return;
            }

            // Preferred time is mandatory
            if (timeComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a preferred time!");
                return;
            }

            String preferredTime =
                    (String) timeComboBox.getSelectedItem();

            dispose();

            new TokenQueueFrame(
                    department,
                    doctor,
                    selectedPriority,
                    proofPath,
                    preferredTime
            ).setVisible(true);
        }

        // Back to doctor selection
        else if (e.getSource() == backButton) {

            dispose();

            // Keep the existing DoctorFrame constructor.
            new DoctorFrame(department, doctor).setVisible(true);
        }
    }
}
