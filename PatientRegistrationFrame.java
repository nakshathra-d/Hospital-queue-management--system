import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientRegistrationFrame extends JFrame
        implements ActionListener {

    JLabel title, nameLabel, ageLabel, phoneLabel;
    JTextField nameField, ageField, phoneField;
    JButton registerButton, clearButton, backButton;

    PatientRegistrationFrame() {

        setTitle("Patient Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Heading
        title = new JLabel(
                "PATIENT REGISTRATION", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBackground(new Color(25, 55, 100));
        title.setOpaque(true);
        title.setBorder(
                BorderFactory.createEmptyBorder(25, 10, 25, 10));

        add(title, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameLabel = new JLabel("Patient Name:");
        ageLabel = new JLabel("Age:");
        phoneLabel = new JLabel("Phone Number:");

        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();

        JLabel[] labels = {
            nameLabel, ageLabel, phoneLabel
        };

        JTextField[] fields = {
            nameField, ageField, phoneField
        };

        for (int i = 0; i < labels.length; i++) {

            labels[i].setFont(
                    new Font("Arial", Font.PLAIN, 18));

            fields[i].setFont(
                    new Font("Arial", Font.PLAIN, 16));

            fields[i].setPreferredSize(
                    new Dimension(250, 38));

            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 25, 25));

        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        JButton[] buttons = {
            registerButton, clearButton, backButton
        };

        for (JButton button : buttons) {
            button.setFont(
                    new Font("Arial", Font.BOLD, 17));

            button.setPreferredSize(
                    new Dimension(140, 45));

            button.setFocusPainted(false);
            button.addActionListener(this);
        }

        registerButton.setBackground(
                new Color(25, 100, 180));
        registerButton.setForeground(Color.WHITE);

        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Window size
        setSize(900, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || age.isEmpty()
                    || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this, "Please fill all fields!");

            } else {

                JOptionPane.showMessageDialog(
                        this, "Patient Registered Successfully!");

                dispose();
                new DepartmentFrame();
            }
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
        new PatientRegistrationFrame();
    }
        }
