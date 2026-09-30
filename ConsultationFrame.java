import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultationFrame extends JFrame implements ActionListener {

    JLabel titleLabel, tokenLabel, nameLabel, consultationLabel;
    JTextField tokenField, nameField, consultationField;
    JButton completeButton, clearButton;

    ConsultationFrame() {

        setTitle("Doctor Consultation");
        setSize(450, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Doctor Consultation", JLabel.CENTER);
        tokenLabel = new JLabel("Token Number:");
        nameLabel = new JLabel("Patient Name:");
        consultationLabel = new JLabel("Consultation:");

        // Text Fields
        tokenField = new JTextField();
        nameField = new JTextField();
        consultationField = new JTextField();

        // Buttons
        completeButton = new JButton("Complete");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(tokenLabel);
        add(tokenField);

        add(nameLabel);
        add(nameField);

        add(consultationLabel);
        add(consultationField);

        add(completeButton);
        add(clearButton);

        // Event Handling
        completeButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == completeButton) {

            String token = tokenField.getText();
            String name = nameField.getText();
            String consultation = consultationField.getText();

            if (token.equals("") || name.equals("")
                    || consultation.equals("")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter all details"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Consultation Completed Successfully"
                );
            }
        }

        if (e.getSource() == clearButton) {

            tokenField.setText("");
            nameField.setText("");
            consultationField.setText("");
        }
    }

    public static void main(String[] args) {
        new ConsultationFrame();
    }
}
