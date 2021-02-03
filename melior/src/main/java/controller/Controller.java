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

    public void createBooking(Object[] bookingInfo) {
        try {
            sqlConnector.createBooking(bookingInfo);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getBookingsByDoctor(String empNbr) {
        try {
            List<Object[]> bookings = sqlConnector.getBookingsByDoctor(empNbr);
            return bookings;

        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getAllBookings() {
        try {
            List<Object[]> bookings = sqlConnector.getAllBookings();
            return bookings;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editAvailability(Object[] availability) {
        try {
            sqlConnector.editAvailability(availability);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object[] getAvailability(String empNbr) {
        try {
            Object[] availability = sqlConnector.getAvailability(empNbr);
            return availability;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMedicalRecord(String[] medicalRecordData) {
        try {
            sqlConnector.addMedicalRecord(medicalRecordData);
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getMedicalRecords(String medicalID) {

        try {
            List<Object[]> medicalRecords = sqlConnector.getMedicalRecords(medicalID);
            return medicalRecords;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getPatientsRelatedToDoctor(String empNbr) {
        try {
            List<Object[]> patients = sqlConnector.getPatientsRelatedToDoctor(empNbr);
            return patients;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public Object[] getDoctorByEmpNbr(String empNbr) {

        try {
            Object[] doctorInfo = sqlConnector.getDoctorByEmpNbr(empNbr);
            return doctorInfo;
        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public void editPatient(String[] patientInfo) {
        try {
            sqlConnector.editPatient(patientInfo);
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
