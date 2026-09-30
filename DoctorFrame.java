import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoctorFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JButton doctor1Button, doctor2Button, doctor3Button;

    DoctorFrame() {

        setTitle("Doctor Selection");
        setSize(450, 300);
        setLayout(new GridLayout(4, 1, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        titleLabel = new JLabel("Select Doctor", JLabel.CENTER);

        // Doctor buttons
        doctor1Button = new JButton("Dr. Arun - General Medicine");
        doctor2Button = new JButton("Dr. Meera - Cardiology");
        doctor3Button = new JButton("Dr. Rahul - ENT");

        // Add components
        add(titleLabel);
        add(doctor1Button);
        add(doctor2Button);
        add(doctor3Button);

        // Event Handling
        doctor1Button.addActionListener(this);
        doctor2Button.addActionListener(this);
        doctor3Button.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == doctor1Button) {
            JOptionPane.showMessageDialog(this,
                    "Dr. Arun Selected");
        }

        if (e.getSource() == doctor2Button) {
            JOptionPane.showMessageDialog(this,
                    "Dr. Meera Selected");
        }

        if (e.getSource() == doctor3Button) {
            JOptionPane.showMessageDialog(this,
                    "Dr. Rahul Selected");
        }
    }

    public static void main(String[] args) {
        new DoctorFrame();
    }
}