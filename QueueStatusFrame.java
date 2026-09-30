import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QueueStatusFrame extends JFrame implements ActionListener {

    JLabel titleLabel, currentLabel, waitingLabel, completedLabel;
    JTextField currentField, waitingField, completedField;
    JButton updateButton, clearButton;

    QueueStatusFrame() {

        setTitle("Queue Status");
        setSize(450, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Queue Status", JLabel.CENTER);
        currentLabel = new JLabel("Current Token:");
        waitingLabel = new JLabel("Waiting Patients:");
        completedLabel = new JLabel("Completed Patients:");

        // Text Fields
        currentField = new JTextField();
        waitingField = new JTextField();
        completedField = new JTextField();

        // Buttons
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(currentLabel);
        add(currentField);

        add(waitingLabel);
        add(waitingField);

        add(completedLabel);
        add(completedField);

        add(updateButton);
        add(clearButton);

        // Event Handling
        updateButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateButton) {

            currentField.setText("3");
            waitingField.setText("5");
            completedField.setText("2");

            JOptionPane.showMessageDialog(
                    this,
                    "Queue Status Updated"
            );
        }

        if (e.getSource() == clearButton) {

            currentField.setText("");
            waitingField.setText("");
            completedField.setText("");
        }
    }

    public static void main(String[] args) {
        new QueueStatusFrame();
    }
}
