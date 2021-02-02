package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorAppointmentPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private JPanel northPanel, centerPanel, southPanel;

    private JTextField tfEmployeeNumber;

    private JLabel lblEmployeeNumber, lblEmpty, lblMonday, lblTuesday, lblWednesday, lblThursday, lblFriday,
                    lblFirst, lblSecond, lblThird, lblFourth, lblEmpName;

    private JButton buttonLogin, buttonSaveAvailability;

    private JTable tableUpcomingAppointments;

    private DefaultTableModel modelUpcomingAppointments;

    private JScrollPane scrollPaneUpcomingAppointments;

    private JCheckBox[] checkBoxes;

    private DoctorPanel doctorPanel;


    public DoctorAppointmentPanel(int width, int height, Controller controller, DoctorPanel doctorPanel) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.doctorPanel = doctorPanel;
        setupPanel();
        addListeners();
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
        northPanel.add(lblEmpName);
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

        centerPanel.add(buttonSaveAvailability);


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
        lblEmpName = new JLabel();
    }
    private void addListeners() {
        ButtonListener buttonListener = new ButtonListener();
        buttonLogin.addActionListener(buttonListener);
    }

    public void login() {
        Object[] empInfo = controller.getDoctorByEmpNbr(tfEmployeeNumber.getText());

        if(empInfo != null) {
            lblEmpName.setText((String)empInfo[1]);
            tfEmployeeNumber.setEditable(false);
            doctorPanel.login((String)empInfo[0]);
        }
        else {
            JOptionPane.showMessageDialog(null, "Employee does not exist, try again");
        }
    }



    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonLogin) {
                login();
            }
        }
    }
}
