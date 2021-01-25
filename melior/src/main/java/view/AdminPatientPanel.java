package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPatientPanel extends JPanel {

    private int width, height;

    private Controller controller;

    private JPanel patientListPanel, medicalRecordPanel;

    private DefaultTableModel modelPatientList, modelMedicalRecords;

    private JTable tablePatients, tableMedicalRecords;

    private JScrollPane scrollpanePatients, scrollpaneMedicalRecords;


    public AdminPatientPanel(int width, int height, Controller controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        setBorder(BorderFactory.createTitledBorder("Patient info"));
        setPreferredSize(new Dimension(width/2-5, height-5));
        //setBackground(Color.RED);
        setLayout(new BorderLayout());

        setupMedicalRecordsPanel();
        setupPatientListPanel();

        add(patientListPanel, BorderLayout.NORTH);
        add(medicalRecordPanel, BorderLayout.SOUTH);
    }

    private void setupPatientListPanel() {
        patientListPanel = new JPanel();
        patientListPanel.setPreferredSize(new Dimension(width/2-5, height/2-20));
        patientListPanel.setLayout(new BorderLayout());
        //patientListPanel.setBackground(Color.CYAN);

        String[] columns = {"Medical number", "Name", "Person number", "Address", "Phone", "Gender", "Total"};
        modelPatientList = new DefaultTableModel(columns, 50) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablePatients = new JTable(modelPatientList);
        tablePatients.setFillsViewportHeight(true);
        tablePatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePatients.setPreferredSize(new Dimension(width/2-30, height/2-40));

        scrollpanePatients = new JScrollPane(tablePatients);
        scrollpanePatients.setPreferredSize(new Dimension(width/2-10, height/2-40));

        patientListPanel.add(scrollpanePatients, BorderLayout.CENTER);

    }

    private void setupMedicalRecordsPanel() {
        medicalRecordPanel = new JPanel();
        medicalRecordPanel.setPreferredSize((new Dimension(width/2-5, height/2-5)));
        medicalRecordPanel.setLayout(new BorderLayout());
        //medicalRecordPanel.setBackground(Color.PINK);

        String[] columns = {"Diagnosis", "Description", "Drugs", "Doctor", "Date"};
        modelMedicalRecords = new DefaultTableModel(columns, 50) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableMedicalRecords = new JTable(modelMedicalRecords);
        tableMedicalRecords.setFillsViewportHeight(true);
        tableMedicalRecords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableMedicalRecords.setPreferredSize(new Dimension(width/2-30, height/2-40));

        scrollpaneMedicalRecords = new JScrollPane(tableMedicalRecords);
        scrollpaneMedicalRecords.setPreferredSize(new Dimension(width/2-10, height/2-40));

        medicalRecordPanel.add(scrollpaneMedicalRecords, BorderLayout.SOUTH);

    }
}
