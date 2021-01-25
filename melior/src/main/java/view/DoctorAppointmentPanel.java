package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorAppointmentPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private JPanel northPanel, centerPanel, southPanel;

    private JTextField tfEmployeeNumber;

    private JLabel lblEmployeeNumber, lblEmpty, lblMonday, lblTuesday, lblWednesday, lblThursday, lblFriday,
                    lblFirst, lblSecond, lblThird, lblFourth;

    private JButton buttonLogin, buttonSaveAvailability;

    private JTable tableUpcomingAppointments;

    private DefaultTableModel modelUpcomingAppointments;

    private JScrollPane scrollPaneUpcomingAppointments;

    private JCheckBox[] checkBoxes;


    public DoctorAppointmentPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width/2-20, height));
        setLayout(new BorderLayout());
        setBackground(Color.PINK);

        initializeInputs();
        initializeLabels();

        setupNorthPanel();
        setupCenterPanel();
        setupSouthPanel();

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setupNorthPanel() {
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(width/2-10, height/14));
        northPanel.setLayout(new FlowLayout());
        //northPanel.setBackground(Color.BLUE);
        northPanel.setBorder(BorderFactory.createTitledBorder("Login"));

        northPanel.add(lblEmployeeNumber);
        tfEmployeeNumber.setPreferredSize(new Dimension(100,20));
        northPanel.add(tfEmployeeNumber);
        buttonLogin.setPreferredSize(new Dimension(100, 20));
        northPanel.add(buttonLogin);
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(width/2-5, height/2));
        centerPanel.setLayout(new GridLayout(0,6,0,20));
        //centerPanel.setBackground(Color.MAGENTA);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Availability"));

        centerPanel.add(lblEmpty);
        centerPanel.add(lblMonday);
        centerPanel.add(lblTuesday);
        centerPanel.add(lblWednesday);
        centerPanel.add(lblThursday);
        centerPanel.add(lblFriday);

        centerPanel.add(lblFirst);
        for(int i = 0; i<5; i++) {
            centerPanel.add(checkBoxes[i]);
        }

        centerPanel.add(lblSecond);
        for(int i = 5; i<10; i++) {
            centerPanel.add(checkBoxes[i]);
        }

        centerPanel.add(lblThird);
        for(int i = 10; i<15; i++) {
            centerPanel.add(checkBoxes[i]);
        }

        centerPanel.add(lblFourth);
        for(int i = 15; i<20; i++) {
            centerPanel.add(checkBoxes[i]);
        }


    }

    private void setupSouthPanel() {
        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(width/2, height/3+50));
        southPanel.setLayout(new BorderLayout());
        //southPanel.setBackground(Color.CYAN);
        southPanel.setBorder(BorderFactory.createTitledBorder("Appointments"));

        String[] columns = {"Date", "Patient"};
        modelUpcomingAppointments = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableUpcomingAppointments = new JTable(modelUpcomingAppointments);
        tableUpcomingAppointments.setFillsViewportHeight(true);
        tableUpcomingAppointments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableUpcomingAppointments.setPreferredSize(new Dimension(width/2-15, height/2-5));

        scrollPaneUpcomingAppointments = new JScrollPane(tableUpcomingAppointments);
        scrollPaneUpcomingAppointments.setPreferredSize(new Dimension(width/2-15, height/2-20));

        southPanel.add(scrollPaneUpcomingAppointments, BorderLayout.CENTER);
    }

    private void initializeInputs() {
        tfEmployeeNumber = new JTextField();
        buttonLogin = new JButton("Login");
        buttonSaveAvailability = new JButton("Save");
        checkBoxes = new JCheckBox[20];
        for(int i = 0; i<checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox();
        }
    }

    private void initializeLabels() {
        lblEmployeeNumber = new JLabel("Employee number");
        lblEmpty = new JLabel("");
        lblMonday = new JLabel("Monday");
        lblTuesday = new JLabel("Tuesday");
        lblWednesday = new JLabel("Wednesday");
        lblThursday = new JLabel("Thursday");
        lblFriday = new JLabel("Friday");
        lblFirst = new JLabel("9:00");
        lblSecond = new JLabel("9:30");
        lblThird = new JLabel("10:00");
        lblFourth = new JLabel("10:30");
    }
}
