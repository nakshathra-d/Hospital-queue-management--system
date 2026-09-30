import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminReportFrame extends JFrame implements ActionListener {

    JLabel titleLabel, totalLabel, completedLabel, waitingLabel;
    JTextField totalField, completedField, waitingField;
    JButton showButton, clearButton;

    AdminReportFrame() {

        setTitle("Admin Report");
        setSize(450, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Hospital Queue Report", JLabel.CENTER);
        totalLabel = new JLabel("Total Patients:");
        completedLabel = new JLabel("Completed Patients:");
        waitingLabel = new JLabel("Waiting Patients:");

        // Text Fields
        totalField = new JTextField();
        completedField = new JTextField();
        waitingField = new JTextField();

        // Buttons
        showButton = new JButton("Show Report");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(totalLabel);
        add(totalField);

        add(completedLabel);
        add(completedField);

        add(waitingLabel);
        add(waitingField);

        add(showButton);
        add(clearButton);

        // Event Handling
        showButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showButton) {

            totalField.setText("10");
            completedField.setText("6");
            waitingField.setText("4");

            JOptionPane.showMessageDialog(
                    this,
                    "Report Displayed Successfully"
            );
        }

        if (e.getSource() == clearButton) {

            totalField.setText("");
            completedField.setText("");
            waitingField.setText("");
        }
    }

    public static void main(String[] args) {
        new AdminReportFrame();
    }
}
