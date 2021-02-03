package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.List;

public class DoctorPatientPanel extends JPanel {
    private int width, height;

    private Controller controller;

    private JPanel panelPatientList, panelMedicalRecord;

    private JTable tablePatientList;

    private DefaultTableModel modelPatientList;

    private JScrollPane scrollpanePatientList;

    private JLabel lblDoctor, lblDate, lblDiagnosis, lblDrugs, lblDescription;

    private JTextField tfDoctor, tfDate, tfDiagnosis, tfDrugs;

    private JTextArea textDescription;

    private JButton buttonAddMedicalRecord;


    public DoctorPatientPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setPreferredSize(new Dimension(width/2-10, height));
        setLayout(new BorderLayout());
        //setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createTitledBorder("Patients"));

        initializeInput();
        initializeLabels();

        setupPanelPatientList();
        setupPanelMedicalRecord();

        add(panelPatientList, BorderLayout.NORTH);
        add(panelMedicalRecord, BorderLayout.SOUTH);

        addButtonListener();
    }

    private void setupPanelPatientList() {
        panelPatientList = new JPanel();
        panelPatientList.setPreferredSize(new Dimension(width/2-10, height/2-30));
        panelPatientList.setLayout(new BorderLayout());
        //panelPatientList.setBackground(Color.GREEN);

        String[] columns = {"Medical Number", "Patient"};
        modelPatientList = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePatientList = new JTable(modelPatientList);
        tablePatientList.setFillsViewportHeight(true);
        tablePatientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePatientList.setPreferredSize(new Dimension(width/2-15, height/2-5));

        scrollpanePatientList = new JScrollPane(tablePatientList);
        scrollpanePatientList.setPreferredSize(new Dimension(width/2-15, height/2-20));

        panelPatientList.add(scrollpanePatientList, BorderLayout.CENTER);

    }

    private void setupPanelMedicalRecord() {
        panelMedicalRecord = new JPanel();
        panelMedicalRecord.setPreferredSize(new Dimension(width/2-20, height/2-20));
        panelMedicalRecord.setLayout(new BorderLayout());
        //panelMedicalRecord.setBackground(Color.BLACK);

        JPanel westPanel = new JPanel();
        //westPanel.setBackground(Color.BLACK);
        westPanel.setPreferredSize(new Dimension(width/4-20, height/2-30));
        westPanel.setLayout(new GridLayout(5,2,5,50));
        westPanel.setBorder(BorderFactory.createTitledBorder("Add Medical Record"));

        westPanel.add(lblDate);
        westPanel.add(tfDate);
        java.util.Date today = new java.util.Date();
        java.sql.Date timestamp = new java.sql.Date(today.getTime());
        tfDate.setText(timestamp.toString());
        westPanel.add(lblDoctor);
        westPanel.add(tfDoctor);
        westPanel.add(lblDiagnosis);
        westPanel.add(tfDiagnosis);
        westPanel.add(lblDrugs);
        westPanel.add(tfDrugs);
        westPanel.add(buttonAddMedicalRecord);
        buttonAddMedicalRecord.setEnabled(false);


        JPanel eastPanel = new JPanel();
        //eastPanel.setBackground(Color.MAGENTA);
        eastPanel.setPreferredSize(new Dimension(width/4-10, height/2-30));
        eastPanel.setLayout(new BorderLayout());
        eastPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        //eastPanel.add(lblDescription, BorderLayout.NORTH);
        eastPanel.add(textDescription, BorderLayout.CENTER);

        panelMedicalRecord.add(westPanel, BorderLayout.WEST);
        panelMedicalRecord.add(eastPanel, BorderLayout.EAST);

    }

    private void initializeInput() {
        tfDate = new JTextField();
        tfDate.setEditable(false);
        tfDoctor = new JTextField();
        tfDoctor.setEditable(false);
        tfDiagnosis = new JTextField();
        tfDrugs = new JTextField();
        textDescription = new JTextArea();
        buttonAddMedicalRecord = new JButton("Save Record");
    }

    private void initializeLabels() {
        lblDoctor = new JLabel("Doctor");
        lblDate = new JLabel("Date");
        lblDiagnosis = new JLabel("Diagnosis");
        lblDrugs = new JLabel("Drugs");
        lblDescription = new JLabel("Description");
    }

    private void addButtonListener() {
        ButtonListener buttonListener = new ButtonListener();
        buttonAddMedicalRecord.addActionListener(buttonListener);
    }

    public void login(String empNbr) {
        tfDoctor.setText(empNbr);
        buttonAddMedicalRecord.setEnabled(true);
        updatePatientList(empNbr);
    }

    private void updatePatientList(String empNbr) {
        List<Object[]> patients = controller.getPatientsRelatedToDoctor(empNbr);

        for(int i = 0; i<patients.size(); i++) {
            modelPatientList.addRow(patients.get(i));
        }
    }

    private void addMedicalRecord() {
        String[] medicalRecordData = new String[5];

        medicalRecordData[0] = (String)modelPatientList.getValueAt(tablePatientList.getSelectedRow(), 0);
        medicalRecordData[1] = tfDiagnosis.getText();
        medicalRecordData[2] = textDescription.getText();
        medicalRecordData[3] = tfDrugs.getText();
        medicalRecordData[4] = tfDoctor.getText();

        controller.addMedicalRecord(medicalRecordData);
    }

    private boolean textFieldsValid() {
        boolean validTextFields = true;

        if(tfDate.getText().isEmpty()) {
            validTextFields = false;
            tfDate.setBorder(new LineBorder(Color.RED));
        }
        if(tfDrugs.getText().isEmpty()) {
            validTextFields = false;
            tfDrugs.setBorder(new LineBorder(Color.RED));
        }
        if(tfDiagnosis.getText().isEmpty()) {
            validTextFields = false;
            tfDiagnosis.setBorder(new LineBorder(Color.RED));
        }
        if(textDescription.getText().isEmpty()) {
            validTextFields = false;
            textDescription.setBorder(new LineBorder(Color.RED));
        }

        return validTextFields;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonAddMedicalRecord) {
                if(tablePatientList.getSelectionModel().isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select patient to add record to");
                }
                else {
                    if(!textFieldsValid()) {
                        JOptionPane.showMessageDialog(null, "Please fill in marked fields");
                    }
                    else {
                        addMedicalRecord();
                    }

                }
            }
        }
    }
}
