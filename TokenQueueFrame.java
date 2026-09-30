import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TokenQueueFrame extends JFrame implements ActionListener {

    JLabel titleLabel, nameLabel, tokenLabel, timeLabel;
    JTextField nameField, tokenField, timeField;
    JButton generateButton, clearButton;

    int tokenNumber = 1;

    TokenQueueFrame() {

        setTitle("Token Queue");
        setSize(450, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Token Queue", JLabel.CENTER);
        nameLabel = new JLabel("Patient Name:");
        tokenLabel = new JLabel("Token Number:");
        timeLabel = new JLabel("Approx. Waiting Time:");

        // Text fields
        nameField = new JTextField();
        tokenField = new JTextField();
        timeField = new JTextField();

        // Buttons
        generateButton = new JButton("Generate Token");
        clearButton = new JButton("Clear");

        // Add components
        add(titleLabel);
        add(new JLabel(""));

        add(nameLabel);
        add(nameField);

        add(tokenLabel);
        add(tokenField);

        add(timeLabel);
        add(timeField);

        add(generateButton);
        add(clearButton);

        // Event Handling
        generateButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generateButton) {

            String name = nameField.getText();

            if (name.equals("")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter patient name"
                );

            } else {

                tokenField.setText(String.valueOf(tokenNumber));

                int waitingTime = (tokenNumber - 1) * 10;

                timeField.setText(waitingTime + " minutes");

                JOptionPane.showMessageDialog(
                        this,
                        "Token Generated Successfully"
                );

                tokenNumber++;
            }
        }

        if (e.getSource() == clearButton) {

            nameField.setText("");
            tokenField.setText("");
            timeField.setText("");
        }
    }

    public static void main(String[] args) {
        new TokenQueueFrame();
    }
}
