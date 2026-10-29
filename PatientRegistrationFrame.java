import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientRegistrationFrame extends JFrame
        implements ActionListener {

    JLabel title, nameLabel, ageLabel, phoneLabel;
    JTextField nameField, ageField, phoneField;
    JButton registerButton, clearButton, backButton;

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    PatientRegistrationFrame() {

        setTitle("Patient Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(lightBlue);

        // Heading
        title = new JLabel(
                "PATIENT REGISTRATION", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(white);
        title.setBackground(navyBlue);
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 15, 25, 15));

        add(title, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 220, 245), 2),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(18, 18, 18, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        nameLabel = new JLabel("Patient Name:");
        ageLabel = new JLabel("Age:");
        phoneLabel = new JLabel("Phone Number:");

        nameField = new JTextField(18);
        ageField = new JTextField(18);
        phoneField = new JTextField(18);

        JLabel[] labels = {
            nameLabel, ageLabel, phoneLabel
        };

        JTextField[] fields = {
            nameField, ageField, phoneField
        };

        Font labelFont = new Font("Arial", Font.BOLD, 21);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        for (int i = 0; i < labels.length; i++) {

            labels[i].setFont(labelFont);
            labels[i].setForeground(blue);

            fields[i].setFont(fieldFont);
            fields[i].setForeground(textBlue);
            fields[i].setPreferredSize(
                    new Dimension(320, 45));

            fields[i].setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(160, 190, 225), 1),
                            BorderFactory.createEmptyBorder(
                                    5, 10, 5, 10)));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 1;
            formPanel.add(fields[i], gbc);
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(lightBlue);
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));
        centerPanel.add(formPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 22, 20));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10));

        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        styleButton(registerButton, blue, white, 150, 48);
        styleButton(clearButton, white, navyBlue, 115, 48);
        styleButton(backButton, white, navyBlue, 110, 48);

        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling
        registerButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);

        // Window settings: keep automatic full screen
        setSize(900, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void styleButton(JButton button, Color background,
                             Color foreground, int width, int height) {
        button.setFont(new Font("Arial", Font.BOLD, 19));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(
                8, 15, 8, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String phone = phoneField.getText().trim();

            // Check empty fields
            if (name.isEmpty() || ageText.isEmpty()
                    || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this, "Please fill all fields!");
                return;
            }

            // Validate age
            int age;

            try {
                age = Integer.parseInt(ageText);

                if (age <= 0 || age > 120) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a valid age (1-120).");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this, "Age must be a number!");
                return;
            }

            // Validate phone number
            if (!phone.matches("[0-9]{10}")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid 10-digit phone number!");
                return;
            }

            JOptionPane.showMessageDialog(
                    this, "Patient Registered Successfully!");

            dispose();
            new DepartmentFrame("");
        }

        else if (e.getSource() == clearButton) {

            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");
        }

        else if (e.getSource() == backButton) {

            dispose();
            new LoginFrame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new PatientRegistrationFrame());
    }
        }
