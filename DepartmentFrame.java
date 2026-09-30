import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DepartmentFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JButton generalButton, cardiologyButton, entButton, pediatricsButton;

    DepartmentFrame() {

        setTitle("Department Selection");
        setSize(450, 300);
        setLayout(new GridLayout(5, 1, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        titleLabel = new JLabel("Select Department", JLabel.CENTER);

        // Department buttons
        generalButton = new JButton("General Medicine");
        cardiologyButton = new JButton("Cardiology");
        entButton = new JButton("ENT");
        pediatricsButton = new JButton("Pediatrics");

        // Add components
        add(titleLabel);
        add(generalButton);
        add(cardiologyButton);
        add(entButton);
        add(pediatricsButton);

        // Event Handling
        generalButton.addActionListener(this);
        cardiologyButton.addActionListener(this);
        entButton.addActionListener(this);
        pediatricsButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == generalButton) {
            JOptionPane.showMessageDialog(this,
                    "General Medicine Selected");
        }

        if (e.getSource() == cardiologyButton) {
            JOptionPane.showMessageDialog(this,
                    "Cardiology Selected");
        }

        if (e.getSource() == entButton) {
            JOptionPane.showMessageDialog(this,
                    "ENT Selected");
        }

        if (e.getSource() == pediatricsButton) {
            JOptionPane.showMessageDialog(this,
                    "Pediatrics Selected");
        }
    }

    public static void main(String[] args) {
        new DepartmentFrame();
    }
}
