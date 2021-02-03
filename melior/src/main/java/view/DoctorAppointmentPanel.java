package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        for(int i = 0; i<checkBoxes.length; i++) {
            checkBoxes[i].setEnabled(false);
        }

        centerPanel.add(buttonSaveAvailability);
        buttonSaveAvailability.setEnabled(false);


    }

    private void setupSouthPanel() {
        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(width/2, height/3+50));
        southPanel.setLayout(new BorderLayout());
        //southPanel.setBackground(Color.CYAN);
        southPanel.setBorder(BorderFactory.createTitledBorder("Appointments"));

        String[] columns = {"Patient", "Date", "Time"};
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
        buttonSaveAvailability.addActionListener(buttonListener);
    }

    private void saveAvailability() {
        Object[] availability = new Object[21];

        availability[0] = tfEmployeeNumber.getText();
        if(checkBoxes[0].isSelected() == true) {
            availability[1] = 0;
        } else {
            availability[1] = -1;
        }
        if(checkBoxes[5].isSelected() == true) {
            availability[2] = 0;
        } else {
            availability[2] = -1;
        }
        if(checkBoxes[10].isSelected() == true) {
            availability[3] = 0;
        } else {
            availability[3] = -1;
        }
        if(checkBoxes[15].isSelected() == true) {
            availability[4] = 0;
        } else {
            availability[4] = -1;
        }
        if(checkBoxes[1].isSelected() == true) {
            availability[5] = 0;
        } else {
            availability[5] = -1;
        }
        if(checkBoxes[6].isSelected() == true) {
            availability[6] = 0;
        } else {
            availability[6] = -1;
        }
        if(checkBoxes[11].isSelected() == true) {
            availability[7] = 0;
        } else {
            availability[7] = -1;
        }
        if(checkBoxes[16].isSelected() == true) {
            availability[8] = 0;
        } else {
            availability[8] = -1;
        }
        if(checkBoxes[2].isSelected() == true) {
            availability[9] = 0;
        } else {
            availability[9] = -1;
        }
        if(checkBoxes[7].isSelected() == true) {
            availability[10] = 0;
        } else {
            availability[10] = -1;
        }
        if(checkBoxes[12].isSelected() == true) {
            availability[11] = 0;
        } else {
            availability[11] = -1;
        }
        if(checkBoxes[17].isSelected() == true) {
            availability[12] = 0;
        } else {
            availability[12] = -1;
        }
        if(checkBoxes[3].isSelected() == true) {
            availability[13] = 0;
        } else {
            availability[13] = -1;
        }
        if(checkBoxes[8].isSelected() == true) {
            availability[14] = 0;
        } else {
            availability[14] = -1;
        }
        if(checkBoxes[13].isSelected() == true) {
            availability[15] = 0;
        } else {
            availability[15] = -1;
        }
        if(checkBoxes[18].isSelected() == true) {
            availability[16] = 0;
        } else {
            availability[16] = -1;
        }
        if(checkBoxes[4].isSelected() == true) {
            availability[17] = 0;
        } else {
            availability[17] = -1;
        }
        if(checkBoxes[9].isSelected() == true) {
            availability[18] = 0;
        } else {
            availability[18] = -1;
        }
        if(checkBoxes[14].isSelected() == true) {
            availability[19] = 0;
        } else {
            availability[19] = -1;
        }
        if(checkBoxes[19].isSelected() == true) {
            availability[20] = 0;
        } else {
            availability[20] = -1;
        }

        controller.editAvailability(availability);

    }

    private void setAvailiability() {
        Object[] availability = controller.getAvailability(tfEmployeeNumber.getText());

        if((int)availability[1] == 0) {
            checkBoxes[0].setSelected(true);
        }
        if((int)availability[2] == 0) {
            checkBoxes[5].setSelected(true);
        }
        if((int)availability[3] == 0) {
            checkBoxes[10].setSelected(true);
        }
        if((int)availability[4] == 0) {
            checkBoxes[15].setSelected(true);
        }
        if((int)availability[5] == 0) {
            checkBoxes[1].setSelected(true);
        }
        if((int)availability[6] == 0) {
            checkBoxes[6].setSelected(true);
        }
        if((int)availability[7] == 0) {
            checkBoxes[11].setSelected(true);
        }
        if((int)availability[8] == 0) {
            checkBoxes[16].setSelected(true);
        }
        if((int)availability[9] == 0) {
            checkBoxes[2].setSelected(true);
        }
        if((int)availability[10] == 0) {
            checkBoxes[7].setSelected(true);
        }
        if((int)availability[11] == 0) {
            checkBoxes[12].setSelected(true);
        }
        if((int)availability[12] == 0) {
            checkBoxes[17].setSelected(true);
        }
        if((int)availability[13] == 0) {
            checkBoxes[3].setSelected(true);
        }
        if((int)availability[14] == 0) {
            checkBoxes[8].setSelected(true);
        }
        if((int)availability[15] == 0) {
            checkBoxes[13].setSelected(true);
        }
        if((int)availability[16] == 0) {
            checkBoxes[18].setSelected(true);
        }
        if((int)availability[17] == 0) {
            checkBoxes[4].setSelected(true);
        }
        if((int)availability[18] == 0) {
            checkBoxes[9].setSelected(true);
        }
        if((int)availability[19] == 0) {
            checkBoxes[14].setSelected(true);
        }
        if((int)availability[20] == 0) {
            checkBoxes[19].setSelected(true);
        }
    }

    public void login() {
        Object[] empInfo = controller.getDoctorByEmpNbr(tfEmployeeNumber.getText());

        if(empInfo != null) {
            lblEmpName.setText((String)empInfo[1]);
            tfEmployeeNumber.setEditable(false);
            doctorPanel.login((String)empInfo[0]);
            buttonSaveAvailability.setEnabled(true);
            for(int i = 0; i<checkBoxes.length; i++) {
                checkBoxes[i].setEnabled(true);
            }
            setAvailiability();
            updateAppointments();
        }
        else {
            JOptionPane.showMessageDialog(null, "Employee does not exist, try again");
        }
    }

    private void updateAppointments() {
        List<Object[]> bookings = controller.getBookingsByDoctor(tfEmployeeNumber.getText());

        for(int i = 0; i<bookings.size(); i++) {
            Object[] booking = bookings.get(i);

            Object[] displayBooking = new Object[3];
            displayBooking[0] = booking[1];
            displayBooking[1] = booking[2];
            displayBooking[2] = booking[5];
            modelUpcomingAppointments.addRow(displayBooking);
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonLogin) {
                login();
            }
            if(e.getSource() == buttonSaveAvailability) {
                saveAvailability();
            }
        }
    }
}
