import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientRegistrationFrame extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, ageLabel, phoneLabel;
    JTextField nameField, ageField, phoneField;
    JButton registerButton, clearButton;

    PatientRegistrationFrame() {

        setTitle("Patient Registration");
        setSize(450, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Patient Registration");
        nameLabel = new JLabel("Patient Name:");
        ageLabel = new JLabel("Age:");
        phoneLabel = new JLabel("Phone Number:");

        // Text Fields
        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();

        // Buttons
        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(nameLabel);
        add(nameField);

        add(ageLabel);
        add(ageField);

        add(phoneLabel);
        add(phoneField);

        add(registerButton);
        add(clearButton);

        // Event Handling
        registerButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            String name = nameField.getText();
            String age = ageField.getText();
            String phone = phoneField.getText();

            if (name.equals("") || age.equals("") || phone.equals("")) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter all details"
                );

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    "Patient Registered Successfully"
                );
            }
        }

        if (e.getSource() == clearButton) {

            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");
        }
    }

    public static void main(String[] args) {
        new PatientRegistrationFrame();
    }
}
