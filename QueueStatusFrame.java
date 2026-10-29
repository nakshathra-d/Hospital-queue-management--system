import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class QueueStatusFrame extends JFrame {

    private String department, doctor, priority, preferredTime;
    private String patientName, patientToken, waitingTime;

    private DefaultTableModel tableModel;

    private final Color navyBlue = new Color(20, 55, 105);
    private final Color blue = new Color(35, 105, 190);
    private final Color lightBlue = new Color(235, 244, 255);
    private final Color white = Color.WHITE;
    private final Color textBlue = new Color(25, 55, 95);

    public QueueStatusFrame(String department, String doctor,
                            String priority, String preferredTime,
                            String patientName, String patientToken,
                            String waitingTime) {

        this.department = department;
        this.doctor = doctor;
        this.priority = priority;
        this.preferredTime = preferredTime;
        this.patientName = patientName;
        this.patientToken = patientToken;
        this.waitingTime = waitingTime;

        setTitle("Queue Status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 650));

        JPanel mainPanel = new JPanel(
                new BorderLayout(18, 20));
        mainPanel.setBackground(lightBlue);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(22, 30, 22, 30));

        // Heading
        JLabel heading = new JLabel(
                "QUEUE STATUS", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(white);
        heading.setBackground(navyBlue);
        heading.setOpaque(true);
        heading.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Patient details
        JPanel detailsPanel = new JPanel(
                new GridLayout(0, 2, 18, 14));
        detailsPanel.setBackground(white);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 215, 245), 2),
                        "Patient Details"),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)));

        Font detailsFont = new Font("Arial", Font.PLAIN, 19);

        addDetail(detailsPanel, "Department:", department, detailsFont);
        addDetail(detailsPanel, "Doctor:", doctor, detailsFont);
        addDetail(detailsPanel, "Patient Name:", patientName, detailsFont);
        addDetail(detailsPanel, "Your Token:", patientToken, detailsFont);
        addDetail(detailsPanel, "Priority:", priority, detailsFont);
        addDetail(detailsPanel, "Estimated Wait:",
                waitingTime + " minutes", detailsFont);

        // Queue table
        tableModel = new DefaultTableModel(
                new String[]{
                    "Token", "Patient Name", "Priority",
                    "Queue Position", "Waiting Time (minutes)"
                }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable queueTable = new JTable(tableModel);
        queueTable.setFont(new Font("Arial", Font.PLAIN, 17));
        queueTable.setForeground(textBlue);
        queueTable.setBackground(white);
        queueTable.setRowHeight(35);
        queueTable.setSelectionBackground(
                new Color(205, 225, 250));
        queueTable.setSelectionForeground(navyBlue);
        queueTable.setFillsViewportHeight(true);
        queueTable.setGridColor(
                new Color(215, 228, 245));
        queueTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader tableHeader = queueTable.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16));
        tableHeader.setBackground(navyBlue);
        tableHeader.setForeground(white);
        tableHeader.setPreferredSize(new Dimension(100, 42));
        tableHeader.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(queueTable);
        scrollPane.getViewport().setBackground(white);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(
                        new Color(190, 215, 245), 2),
                "Doctor's Queue"));

        JPanel centerPanel = new JPanel(
                new BorderLayout(15, 15));
        centerPanel.setBackground(lightBlue);
        centerPanel.add(detailsPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JButton updateButton = new JButton("Update Queue");
        JButton backButton = new JButton("Back");
        JButton finishButton = new JButton("Finish");

        JButton[] buttons = {
            updateButton, backButton, finishButton
        };

        Dimension buttonSize = new Dimension(190, 50);

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setPreferredSize(buttonSize);
            button.setFocusPainted(false);
            button.setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setBorder(BorderFactory.createEmptyBorder(
                    8, 12, 8, 12));
        }

        updateButton.setBackground(blue);
        updateButton.setForeground(white);

        backButton.setBackground(white);
        backButton.setForeground(navyBlue);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(navyBlue, 1),
                BorderFactory.createEmptyBorder(7, 11, 7, 11)));

        finishButton.setBackground(navyBlue);
        finishButton.setForeground(white);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 22, 12));
        buttonPanel.setBackground(navyBlue);
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        buttonPanel.add(finishButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Update queue
        updateButton.addActionListener(e -> refreshQueue());

        // Back to token queue
        backButton.addActionListener(e -> {
            dispose();
            new TokenQueueFrame(
                    department, doctor, priority, "", preferredTime
            ).setVisible(true);
        });

        // Finish
        finishButton.addActionListener(e -> dispose());

        setContentPane(mainPanel);

        refreshQueue();

        // Automatically maximize on desktop
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addDetail(JPanel panel, String label,
                           String value, Font font) {

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(
                new Font("Arial", Font.BOLD, font.getSize()));
        labelComponent.setForeground(blue);

        JLabel valueComponent = new JLabel(
                value == null ? "" : value);
        valueComponent.setFont(font);
        valueComponent.setForeground(textBlue);

        panel.add(labelComponent);
        panel.add(valueComponent);
    }

    private void refreshQueue() {
        tableModel.setRowCount(0);

        TokenQueueFrame.updateQueueOrder();

        for (TokenQueueFrame.PatientRecord p
                : TokenQueueFrame.patientList) {

            if (p.department != null && p.doctor != null
                    && p.department.equalsIgnoreCase(department)
                    && p.doctor.equalsIgnoreCase(doctor)) {

                tableModel.addRow(new Object[]{
                    p.token,
                    p.name,
                    p.priority,
                    p.queuePosition,
                    p.waitTime
                });
            }
        }
    }
}
