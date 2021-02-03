package connection;

import javax.swing.table.DefaultTableModel;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnector {

    public Connection connect() throws SQLException, UnknownHostException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String user = "andreasmansson";
        String pw = "123";

        String dbURL = "jdbc:sqlserver://localhost";

        Connection connection = DriverManager.getConnection(dbURL, user, pw);
        if(connection != null) {
            System.out.println("Connected to database");
            return connection;
        }
        return null;
    }

//    public DefaultTableModel getProcedures() throws SQLException, UnknownHostException, ClassNotFoundException {
//        Connection connection = connect();
//
//        List<Object[]> data = new ArrayList<>();
//
//        String execProcedure = "exec Health_Center.dbo.get_procedure";
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(execProcedure);
//        ResultSetMetaData metaData = resultSet.getMetaData();
//
//        int columnCount = metaData.getColumnCount();
//        String[] columnNames = new String[columnCount];
//
//        for(int column = 0; column < columnCount; column++) {
//            columnNames[column] = metaData.getColumnName(column + 1).toUpperCase();
//        }
//
//        while(resultSet.next()) {
//            Object[] row = new Object[columnCount];
//            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
//                row[columnIndex] = resultSet.getObject(columnIndex + 1);
//            }
//            data.add(row);
//        }
//
//        return new DefaultTableModel(data.toArray(new Object[data.size()][columnCount]), columnNames);
//    }

    public Object[] getProcedures() throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_procedure";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(execProcedure);

        List<String> data = new ArrayList<>();

        while(resultSet.next()) {
            data.add(resultSet.getString(1));
        }

        return data.toArray();
    }

    public void addDoctor(String[] doctor) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.add_doctors ?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        for(int i = 0; i<doctor.length; i++) {
            statement.setString(i+1, doctor[i]);
        }
        statement.execute();

        execProcedure = "exec Health_Center.dbo.create_appointment ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

        PreparedStatement preparedStatement = connection.prepareStatement(execProcedure);

        preparedStatement.setString(1, doctor[0]);

        for(int i = 1; i<=20; i++) {
            preparedStatement.setInt(i+1, 0);
        }

        preparedStatement.execute();
    }

    public void editAvailability(Object[] availability) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.update_appointment ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, (String)availability[0]);

        for(int i = 1; i<availability.length; i++) {
            statement.setInt(i+1, (int)availability[i]);
        }

        statement.execute();
    }

    public Object[] getAvailability(String empNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_appointment ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, empNbr);
        ResultSet resultSet = statement.executeQuery();

        Object[] availability = new Object[21];

        resultSet.next();

        for(int i = 0; i<availability.length; i++) {
            availability[i] = resultSet.getObject(i+1);
        }

        return availability;
    }

    public void createBooking(Object[] bookingData) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.new_booking ?,?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, (String)bookingData[0]);
        statement.setString(2, (String)bookingData[1]);
        statement.setDate(3, (java.sql.Date)bookingData[2]);
        statement.setString(4, (String)bookingData[3]);
        statement.setString(5, (String)bookingData[4]);
        statement.setString(6, (String)bookingData[5]);

        statement.execute();
    }

    public List<Object[]> getBookingsByDoctor(String empNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_doctor_appointment ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, empNbr);

        ResultSet resultSet = statement.executeQuery();

        List<Object[]> data = new ArrayList<>();

        while(resultSet.next()) {
            Object[] row = new Object[6];
            row[0] = resultSet.getString(1);
            row[1] = resultSet.getString(2);
            row[2] = resultSet.getDate(3);
            row[3] = resultSet.getString(4);
            row[4] = resultSet.getString(5);
            row[5] = resultSet.getString(6);
            data.add(row);
        }
        return data;
    }

    public List<Object[]> getAllBookings() throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_all_booking";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(execProcedure);

        List<Object[]> bookings = new ArrayList<>();

        while(resultSet.next()) {
            Object[] row = new Object[5];

            row[0] = resultSet.getString(1);
            row[1] = resultSet.getDate(3);
            row[2] = resultSet.getString(2);
            row[3] = resultSet.getString(5);
            row[4] = resultSet.getString(6);
            bookings.add(row);
        }

        return bookings;
    }

    public List<Object[]> getDoctors() throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();
        List<Object[]> data = new ArrayList<>();
        String execProcedure = "exec Health_Center.dbo.get_doctors";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(execProcedure);

        while(resultSet.next()) {
            Object[] row = new Object[3];
            String firstName = (String)resultSet.getObject(2);
            String lastName = (String)resultSet.getObject(3);
            String fullName = firstName + " " + lastName;
            row[0] = resultSet.getObject(1);
            row[1] = fullName;
            row[2] = resultSet.getObject(4);

            data.add(row);

        }

        return data;
    }

    public List<Object[]> getDoctorsBySpec(String specialization) throws SQLException, UnknownHostException, ClassNotFoundException{
        Connection connection = connect();
        List<Object[]> data = new ArrayList<>();
        String execProcedure = "exec Health_Center.dbo.get_employee_procedure ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, specialization);

        ResultSet resultSet = statement.executeQuery();

        execProcedure = "SELECT * FROM Health_Center.dbo.summary_procedures WHERE procedure_type='" + specialization + "';";
        Statement statementCost = connection.createStatement();
        ResultSet resultSetCost = statementCost.executeQuery(execProcedure);
        resultSetCost.next();

        String cost = resultSetCost.getString(2);
        String fullName;
        while(resultSet.next()) {
            Object[] row = new Object[4];
            fullName = resultSet.getString(2) + " " + resultSet.getString(3);
            row[0] = resultSet.getString(1);
            row[1] = fullName;
            row[2] = specialization;
            row[3] = cost;
            data.add(row);
        }

        return data;
    }

    public Object[] getDoctorByEmpNbr(String empNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "SELECT * FROM Health_Center.dbo.employee WHERE employee_id='" + empNbr + "';";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(execProcedure);

        if(resultSet.next() == false) {
            return null;
        }

        Object[] employeeData = new Object[2];
        employeeData[0] = resultSet.getString(1);
        employeeData[1] = resultSet.getString(2) + " " + resultSet.getString(3);

        return employeeData;
    }

    public void deleteDoctor(String empNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.delete_employee ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, empNbr);
        statement.execute();
    }

    public void addSpecs(List<Object[]> data) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.add_procedure ?,?";

        for(int i = 0; i<data.size(); i++) {
            PreparedStatement statement = connection.prepareStatement(execProcedure);
            Object[] row = data.get(i);
            statement.setString(1, (String)row[0]);
            statement.setString(2, (String)row[1]);
            statement.execute();
        }
    }

    public String[] getPatient(String personNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_patient_person_id ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, personNbr);

        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        if(resultSet.next() == false) {
            return null;
        }

        String[] patientResult = new String[metaData.getColumnCount()];

        for(int i = 0; i<patientResult.length; i++) {

            patientResult[i] = resultSet.getString(i+1);

        }

        return patientResult;
    }

    public List<Object[]> getPatientsRelatedToDoctor(String empNbr) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_doctor_patient_appointment ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, empNbr);
        ResultSet resultSet = statement.executeQuery();

        List<Object[]> patients = new ArrayList<>();

        while(resultSet.next()) {
            Object[] row = new Object[2];

            row[0] = resultSet.getString(3);
            row[1] = resultSet.getString(1) + " " + resultSet.getString(2);

            patients.add(row);
        }

        return patients;
    }


    public void addPatient(String[] patientInfo) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.insert_patient ?,?,?,?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, patientInfo[0]);
        statement.setString(2, patientInfo[1]);
        statement.setString(3, patientInfo[2]);
        statement.setString(4, patientInfo[3]);
        statement.setString(5, patientInfo[4]);
        statement.setString(6, patientInfo[5]);
        statement.setString(8, patientInfo[6]);

        java.util.Date today = new java.util.Date();
        java.sql.Date timestamp = new java.sql.Date(today.getTime());

        statement.setDate(7, timestamp);

        statement.execute();
    }

    public void editPatient(String[] patientInfo) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.update_patient ?,?,?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, patientInfo[0]);
        statement.setString(2, patientInfo[1]);
        statement.setString(3, patientInfo[2]);
        statement.setString(4, patientInfo[3]);
        statement.setString(5, patientInfo[4]);
        statement.setString(6, patientInfo[5]);
        statement.setString(7, patientInfo[6]);

        statement.execute();
    }

    public List<Object[]> getAllPatients() throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_all_patients";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(execProcedure);

        List<Object[]> data = new ArrayList<>();

        execProcedure = "exec Health_Center.dbo.total_price ?";

        while(resultSet.next()) {
            Object[] row = new Object[7];
            row[0] = resultSet.getString(1);
            row[1] = resultSet.getString(3) + " " + resultSet.getString(4);
            row[2] = resultSet.getString(2);
            row[3] = resultSet.getString(6);
            row[4] = resultSet.getString(5);
            row[5] = resultSet.getString(8);

            PreparedStatement costStatement = connection.prepareStatement(execProcedure);
            costStatement.setString(1, resultSet.getString(1));
            ResultSet costResult = costStatement.executeQuery();
            costResult.next();
            row[6] = costResult.getInt(1);
            data.add(row);
        }

        return data;
    }

    public void addMedicalRecord(String[] medicalRecordData) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.new_medical_record ?,?,?,?,?,?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);

        statement.setString(1, medicalRecordData[0]);
        statement.setString(2, medicalRecordData[1]);
        statement.setString(3, medicalRecordData[2]);
        statement.setString(4, medicalRecordData[3]);
        statement.setString(5, medicalRecordData[4]);

        java.util.Date today = new java.util.Date();
        java.sql.Date timestamp = new java.sql.Date(today.getTime());

        statement.setDate(6, timestamp);

        statement.execute();
    }

    public List<Object[]> getMedicalRecords(String medicalID) throws SQLException, UnknownHostException, ClassNotFoundException {
        Connection connection = connect();

        String execProcedure = "exec Health_Center.dbo.get_medical_record ?";

        PreparedStatement statement = connection.prepareStatement(execProcedure);
        statement.setString(1, medicalID);
        ResultSet resultSet = statement.executeQuery();

        List<Object[]> data = new ArrayList<>();

        while(resultSet.next()) {
            Object[] row = new Object[5];

            row[0] = resultSet.getString(2);
            row[1] = resultSet.getString(3);
            row[2] = resultSet.getString(4);
            row[3] = resultSet.getString(5);
            row[4] = resultSet.getDate(6);

            data.add(row);
        }

        return data;
    }


    public static void main(String[] args) {
        SQLConnector sqlConnector = new SQLConnector();

//        try {
//            List<Object[]> medicalRecords = sqlConnector.getMedicalRecords("009");
//
//            for(int i = 0; i<medicalRecords.size(); i++) {
//                Object[] row = medicalRecords.get(i);
//
//                for(int j = 0; j<row.length; j++) {
//                    System.out.println(row[j]);
//                }
//            }
//        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
