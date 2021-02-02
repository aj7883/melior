package controller;

import connection.SQLConnector;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    MainFrame mainFrame;
    SQLConnector sqlConnector = new SQLConnector();

    public Controller() {
        mainFrame = new MainFrame(1280, 720, this);
    }

    public List<Object[]> getMedicalRecords(String medicalID) {
        List<Object[]> medicalRecords = new ArrayList<>();
        try {
            medicalRecords = sqlConnector.getMedicalRecords(medicalID);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return medicalRecords;
    }

    public List<Object[]> getAllPatients() {
        List<Object[]> allPatients = new ArrayList<>();
        try {
            allPatients = sqlConnector.getAllPatients();
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allPatients;
    }

    public List<Object[]> getDoctorsBySpecialization(String specialization) {
        List<Object[]> doctorsBySpec = new ArrayList<>();
        try {
            doctorsBySpec = sqlConnector.getDoctorsBySpec(specialization);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doctorsBySpec;
    }

    public void addSpec(List<Object[]> data) {
        try {
            sqlConnector.addSpecs(data);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getDoctors() {
        List<Object[]> allDoctors = new ArrayList<>();
        try {
            allDoctors = sqlConnector.getDoctors();
        }catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allDoctors;
    }

    public void addDoctor(String[] doctor) {
        try {
            sqlConnector.addDoctor(doctor);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(String empNbr) {
        try {
            sqlConnector.deleteDoctor(empNbr);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object[] getSpecializations() {
        try {
            Object[] specs = sqlConnector.getProcedures();
            return specs;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPatient(String[] newPatientInfo) {
        try {
            sqlConnector.addPatient(newPatientInfo);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String[] getPatient(String personNumber) {
        try {
            String[] patientInfo = sqlConnector.getPatient(personNumber);
            return patientInfo;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
