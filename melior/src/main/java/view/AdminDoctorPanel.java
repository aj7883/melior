package view;

import controller.Controller;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminDoctorPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private JPanel panelAppointments, panelSpecializations, panelCreateDoctor, panelDoctorDisplay, panelNorth;

    private JButton buttonAddSpec, buttonSaveSpec, buttonAddDoctor, buttonDeleteDoctor;

    private JLabel lblSpec, lblCost, lblFirstNameDoctor, lblLastNameDoctor, lblPhoneDoctor, lblSpecDoctor;

    private JTextField tfSpec, tfCost, tfFirstNameDoctor, tfLastNameDoctor, tfPhoneDoctor;

    private JTable tableNewSpecs, tableAllDoctors, tableAppointments;

    private DefaultTableModel modelNewSpecs, modelAllDoctors, modelAppointments;

    private JScrollPane scrollpaneNewSpecs, scrollpaneAllDoctors, scrollpaneAppointments;

    private JComboBox comboBoxSpecs;

    public AdminDoctorPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(width/2-5, height-5));
        //setBackground(Color.GREEN);
        setLayout(new BorderLayout());

        initializeButtons();
        initializeLabels();
        initializeTextFields();

        setListeners();

        setupNorthPanel();
        add(panelNorth, BorderLayout.NORTH);

        setupAppointmentsPanel();
        add(panelAppointments, BorderLayout.SOUTH);

        updateDoctorTable(controller.getDoctors());
    }

    private void setupNorthPanel() {
        panelNorth = new JPanel();
        panelNorth.setPreferredSize(new Dimension(width/2-5, height/2-10));
        panelNorth.setLayout(new BorderLayout());
        //panelNorth.setBackground(Color.BLUE);

        setupSpecializationPanel();
        panelNorth.add(panelSpecializations, BorderLayout.WEST);

        setupCreateDoctorPanel();
        panelNorth.add(panelCreateDoctor, BorderLayout.CENTER);

        setupDoctorDisplayPanel();
        panelNorth.add(panelDoctorDisplay, BorderLayout.EAST);

    }

    private void setupAppointmentsPanel() {
        panelAppointments = new JPanel();
        panelAppointments.setPreferredSize(new Dimension(width/2-5, height/2-20));
        panelAppointments.setLayout(new BorderLayout());
        //panelAppointments.setBackground(Color.CYAN);
        panelAppointments.setBorder(BorderFactory.createTitledBorder("Upcoming appointments"));

        String[] columns = {"Date", "Patient", "Doctor"};
        modelAppointments = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableAppointments = new JTable(modelAppointments);
        tableAppointments.setFillsViewportHeight(true);
        tableAppointments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAppointments.setPreferredSize(new Dimension(width/2-15, height/2-5));

        scrollpaneAppointments = new JScrollPane(tableAppointments);
        scrollpaneAppointments.setPreferredSize(new Dimension(width/2-15, height/2-5));

        panelAppointments.add(scrollpaneAppointments, BorderLayout.CENTER);


    }

    private void setupDoctorDisplayPanel() {
        panelDoctorDisplay = new JPanel();
        panelDoctorDisplay.setPreferredSize(new Dimension(width/4-5, height/2-5));
        panelDoctorDisplay.setLayout(new BorderLayout());
        //panelDoctorDisplay.setBackground(Color.BLACK);

        String[] columns = {"Emp. nbr.", "Name", "Specialization"};
        modelAllDoctors = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableAllDoctors = new JTable(modelAllDoctors);
        tableAllDoctors.setFillsViewportHeight(true);
        tableAllDoctors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAllDoctors.setPreferredSize(new Dimension(width/4-15, height/4-5));

        scrollpaneAllDoctors = new JScrollPane(tableAllDoctors);
        scrollpaneAllDoctors.setPreferredSize(new Dimension(width/8-15, height/4-5));

        panelDoctorDisplay.add(scrollpaneAllDoctors, BorderLayout.CENTER);
    }

    private void setupSpecializationPanel() {
        panelSpecializations = new JPanel();
        panelSpecializations.setPreferredSize(new Dimension(width/8-5, height/2-5));
        panelSpecializations.setLayout(new BorderLayout());
        panelSpecializations.setBorder(BorderFactory.createTitledBorder("Add specializations"));
        //panelSpecializations.setBackground(Color.PINK);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(width/8-5, height/6-20));
        GridLayout gl = new GridLayout(2,2, 5, 50);
        northPanel.setLayout(gl);
        northPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //northPanel.setBackground(Color.MAGENTA);

        northPanel.add(lblSpec);
        northPanel.add(tfSpec);
        northPanel.add(lblCost);
        northPanel.add(tfCost);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(width/8-5, (height/6-5)*2));
        //southPanel.setBackground(Color.CYAN);
        southPanel.setLayout(new BorderLayout());

        String[] columns = {"Specialization", "Cost"};
        modelNewSpecs = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableNewSpecs = new JTable(modelNewSpecs);
        tableNewSpecs.setFillsViewportHeight(true);
        tableNewSpecs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableNewSpecs.setPreferredSize(new Dimension(width/8-15, height/4-5));

        scrollpaneNewSpecs = new JScrollPane(tableNewSpecs);
        scrollpaneNewSpecs.setPreferredSize(new Dimension(width/8-15, height/4-5));

        southPanel.add(scrollpaneNewSpecs, BorderLayout.NORTH);
        southPanel.add(buttonAddSpec, BorderLayout.CENTER);
        southPanel.add(buttonSaveSpec, BorderLayout.SOUTH);

        panelSpecializations.add(northPanel, BorderLayout.NORTH);
        panelSpecializations.add(southPanel, BorderLayout.SOUTH);
    }

    private void setupCreateDoctorPanel() {
        panelCreateDoctor = new JPanel();
        panelCreateDoctor.setPreferredSize(new Dimension(width/8-5, height/2-5));
        panelCreateDoctor.setLayout(new GridLayout(5, 2, 5, 55));
        panelCreateDoctor.setBorder(BorderFactory.createTitledBorder("Add/delete doctor"));
        //panelCreateDoctor.setBackground(Color.RED);

        comboBoxSpecs = new JComboBox(controller.getSpecializations());

        panelCreateDoctor.add(lblFirstNameDoctor);
        panelCreateDoctor.add(tfFirstNameDoctor);
        panelCreateDoctor.add(lblLastNameDoctor);
        panelCreateDoctor.add(tfLastNameDoctor);
        panelCreateDoctor.add(lblPhoneDoctor);
        panelCreateDoctor.add(tfPhoneDoctor);
        panelCreateDoctor.add(lblSpecDoctor);
        panelCreateDoctor.add(comboBoxSpecs);
        panelCreateDoctor.add(buttonAddDoctor);
        panelCreateDoctor.add(buttonDeleteDoctor);


    }

    private void initializeButtons() {
        buttonAddSpec = new JButton("Add");
        //buttonAddSpec.setPreferredSize(new Dimension(20,20));
        buttonSaveSpec = new JButton("Save");
        //buttonSaveSpec.setPreferredSize(new Dimension(20,20));
        buttonAddDoctor = new JButton("Add");
        buttonDeleteDoctor = new JButton("Delete");
    }

    private void initializeLabels() {
        lblSpec = new JLabel("Specialization");
        lblCost = new JLabel("Cost");
        lblSpecDoctor = new JLabel("Specialization");
        lblFirstNameDoctor = new JLabel("First name");
        lblLastNameDoctor = new JLabel("Last name");
        lblPhoneDoctor = new JLabel("Phone");
    }

    private void initializeTextFields() {
        tfSpec = new JTextField();
        tfCost = new JTextField();
        tfFirstNameDoctor = new JTextField();
        tfLastNameDoctor = new JTextField();
        tfPhoneDoctor = new JTextField();
    }

    private void setListeners() {
        ButtonListener buttonListener = new ButtonListener();
        buttonAddSpec.addActionListener(buttonListener);
        buttonAddDoctor.addActionListener(buttonListener);
        buttonDeleteDoctor.addActionListener(buttonListener);
    }

    private void addSpec() {
        String[] row = {tfSpec.getText(), tfCost.getText()};
        modelNewSpecs.addRow(row);
    }

    private void addDoctor() {
        String[] newDoctor = new String[5];

        String empNumber = "004";

        newDoctor[0] = empNumber;
        newDoctor[1] = tfFirstNameDoctor.getText();
        newDoctor[2] = tfLastNameDoctor.getText();
        newDoctor[3] = comboBoxSpecs.getSelectedItem().toString();
        newDoctor[4] = tfPhoneDoctor.getText();

        controller.addDoctor(newDoctor);
    }

    private void updateDoctorTable(List<Object[]> doctorData) {
        int rows = modelAllDoctors.getRowCount();

        while(rows > 0) {
            modelAllDoctors.removeRow(0);
            rows = modelAllDoctors.getRowCount();
        }

        for(int i = 0; i<doctorData.size(); i++) {
            modelAllDoctors.addRow(doctorData.get(i));
        }

    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonAddSpec) {
                addSpec();
            }
            if(e.getSource() == buttonAddDoctor) {
                if(tfFirstNameDoctor.getText().length() < 1 | tfLastNameDoctor.getText().length() < 1 | tfPhoneDoctor.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields");
                }
                else {
                    addDoctor();
                    updateDoctorTable(controller.getDoctors());
                }
            }
            if(e.getSource() == buttonDeleteDoctor) {
                if(tableAllDoctors.getSelectionModel().isSelectionEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "Please select doctor to delete");
                }
                else {
                    int selectedRow = tableAllDoctors.getSelectedRow();
                    controller.deleteDoctor((String)tableAllDoctors.getValueAt(selectedRow, 0));
                    updateDoctorTable(controller.getDoctors());
                }
            }
        }
    }



}
