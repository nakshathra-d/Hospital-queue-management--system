import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TokenQueueFrame extends JFrame {

    static class PatientRecord {
        String name, department, doctor, priority, proof, preferredTime;
        int token, queuePosition, waitTime, registrationOrder;

        PatientRecord(String name, String department, String doctor,
                      String priority, String proof, String preferredTime,
                      int registrationOrder) {
            this.name = name;
            this.department = department;
            this.doctor = doctor;
            this.priority = priority;
            this.proof = proof;
            this.preferredTime = preferredTime;
            this.registrationOrder = registrationOrder;
        }
    }

    static ArrayList<PatientRecord> patientList = new ArrayList<>();
    static PatientRecord currentPatient;
    static int registrationCounter = 0;

    private String department, doctor, priority, proof, preferredTime;
    private JTextField nameField;
    private JLabel tokenLabel, positionLabel, waitingLabel;

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    public TokenQueueFrame(String department, String doctor,
                           String priority, String proof,
                           String preferredTime) {

        this.department = department;
        this.doctor = doctor;
        this.priority = priority;
        this.proof = proof;
        this.preferredTime = preferredTime;

        setTitle("Token Queue");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(850, 650));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(lightBlue);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // Heading
        JLabel heading = new JLabel(
                "TOKEN QUEUE", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(white);
        heading.setBackground(navyBlue);
        heading.setOpaque(true);
        heading.setBorder(
                BorderFactory.createEmptyBorder(22, 10, 22, 10));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 220, 245), 2),
                        BorderFactory.createEmptyBorder(25, 35, 25, 35)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 19);
        Font valueFont = new Font("Arial", Font.PLAIN, 19);

        addFormRow(formPanel, gbc, 0,
                "Department:", department, labelFont, valueFont);
        addFormRow(formPanel, gbc, 1,
                "Doctor:", doctor, labelFont, valueFont);
        addFormRow(formPanel, gbc, 2,
                "Priority:", priority, labelFont, valueFont);
        addFormRow(formPanel, gbc, 3,
                "Preferred Time:", preferredTime, labelFont, valueFont);

        // Patient name
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(blue);
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(22);
        nameField.setFont(valueFont);
        nameField.setForeground(textBlue);
        nameField.setPreferredSize(new Dimension(300, 42));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(160, 190, 225), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(nameField, gbc);

        // Token details
        tokenLabel = new JLabel("Token: -");
        positionLabel = new JLabel("Queue Position: -");
        waitingLabel = new JLabel("Estimated Waiting Time: -");

        JLabel[] resultLabels = {
            tokenLabel, positionLabel, waitingLabel
        };

        for (JLabel label : resultLabels) {
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setForeground(navyBlue);
            label.setBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        formPanel.add(tokenLabel, gbc);

        gbc.gridy = 6;
        formPanel.add(positionLabel, gbc);

        gbc.gridy = 7;
        formPanel.add(waitingLabel, gbc);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(lightBlue);
        centerPanel.add(formPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JButton generateButton = new JButton("Generate Token");
        JButton continueButton = new JButton("Continue");
        JButton backButton = new JButton("Back");

        JButton[] buttons = {
            generateButton, continueButton, backButton
        };

        Dimension buttonSize = new Dimension(190, 52);

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setPreferredSize(buttonSize);
            button.setFocusPainted(false);
            button.setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setBorder(BorderFactory.createEmptyBorder(
                    8, 12, 8, 12));
        }

        generateButton.setBackground(blue);
        generateButton.setForeground(white);

        continueButton.setBackground(navyBlue);
        continueButton.setForeground(white);

        backButton.setBackground(white);
        backButton.setForeground(navyBlue);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(navyBlue, 1),
                BorderFactory.createEmptyBorder(7, 11, 7, 11)));

        JPanel buttonPanel = new JPanel(new FlowLayout(
                FlowLayout.CENTER, 20, 12));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.add(generateButton);
        buttonPanel.add(continueButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Generate token
        generateButton.addActionListener(e -> generateToken());

        // Continue to queue status
        continueButton.addActionListener(e -> {
            if (currentPatient == null
                    || !same(currentPatient.department, department)
                    || !same(currentPatient.doctor, doctor)
                    || !same(currentPatient.priority, priority)
                    || !same(currentPatient.preferredTime, preferredTime)) {

                JOptionPane.showMessageDialog(
                        this, "Please generate your token first.");
                return;
            }

            updateQueueOrder();

            new QueueStatusFrame(
                    department, doctor, priority, preferredTime,
                    currentPatient.name,
                    String.valueOf(currentPatient.token),
                    String.valueOf(currentPatient.waitTime)
            ).setVisible(true);

            dispose();
        });

        // Back to priority selection
        backButton.addActionListener(e -> {
            dispose();

            new PrioritySelectionFrame(
                    department, doctor, priority, proof, preferredTime
            ).setVisible(true);
        });

        setContentPane(mainPanel);

        // Automatically maximize when opened
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc,
                            int row, String label, String value,
                            Font labelFont, Font valueFont) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(labelFont);
        labelComponent.setForeground(blue);
        panel.add(labelComponent, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        JLabel valueComponent = new JLabel(
                value == null ? "" : value);
        valueComponent.setFont(valueFont);
        valueComponent.setForeground(textBlue);
        panel.add(valueComponent, gbc);
    }

    private void generateToken() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please enter patient name.");
            return;
        }

        if (currentPatient != null
                && patientList.contains(currentPatient)
                && same(currentPatient.name, name)
                && same(currentPatient.department, department)
                && same(currentPatient.doctor, doctor)
                && same(currentPatient.priority, priority)
                && same(currentPatient.preferredTime, preferredTime)) {

            updateQueueOrder();
            refreshCurrentPatient();
            JOptionPane.showMessageDialog(
                    this, "This patient already has a token.");
            return;
        }

        currentPatient = new PatientRecord(
                name, department, doctor, priority, proof, preferredTime,
                getNextRegistrationOrder()
        );

        patientList.add(currentPatient);
        updateQueueOrder();
        refreshCurrentPatient();

        JOptionPane.showMessageDialog(
                this, "Token generated successfully!");
    }

    private void refreshCurrentPatient() {
        tokenLabel.setText("Token: " + currentPatient.token);
        positionLabel.setText(
                "Queue Position: " + currentPatient.queuePosition);
        waitingLabel.setText(
                "Estimated Waiting Time: "
                + currentPatient.waitTime + " minutes");
    }

    private static boolean same(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    static synchronized int getNextRegistrationOrder() {
        for (PatientRecord p : patientList) {
            registrationCounter = Math.max(
                    registrationCounter, p.registrationOrder);
        }
        return ++registrationCounter;
    }

    static void updateQueueOrder() {
        ArrayList<String> queues = new ArrayList<>();

        for (PatientRecord p : patientList) {
            String key = p.department + "||" + p.doctor;
            if (!queues.contains(key)) {
                queues.add(key);
            }
        }

        for (String key : queues) {
            String[] parts = key.split("\\|\\|", 2);
            String dept = parts[0];
            String doc = parts[1];

            ArrayList<PatientRecord> queue = new ArrayList<>();

            for (PatientRecord p : patientList) {
                if (same(p.department, dept)
                        && same(p.doctor, doc)) {
                    queue.add(p);
                }
            }

            queue.sort(
                    Comparator.comparingInt(
                            (PatientRecord p) ->
                                    getPriorityRank(p.priority))
                    .thenComparingInt(p -> p.registrationOrder)
            );

            for (int i = 0; i < queue.size(); i++) {
                PatientRecord p = queue.get(i);
                p.token = i + 1;
                p.queuePosition = i + 1;
                p.waitTime = i * 10;
            }
        }
    }

    static int getPriorityRank(String priority) {
        if (priority == null) return 4;

        String value = priority.toLowerCase();

        if (value.contains("pregnant")) return 1;
        if (value.contains("disability")) return 2;
        if (value.contains("senior")) return 3;

        return 4;
    }
}
