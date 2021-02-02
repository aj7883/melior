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

        while(resultSet.next()) {
            Object[] row = new Object[7];
            row[0] = resultSet.getString(1);
            row[1] = resultSet.getString(3) + " " + resultSet.getString(4);
            row[2] = resultSet.getString(2);
            row[3] = resultSet.getString(6);
            row[4] = resultSet.getString(5);
            row[5] = resultSet.getString(8);
            row[6] = "Total placeholder";
            data.add(row);
        }

        return data;
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
            row[3] = resultSet.getString(6);
            row[4] = "Date placeholder";
        }

        return data;
    }

    public static void main(String[] args) {
        SQLConnector sqlConnector = new SQLConnector();

//        try {
//            String[] patientInfo = {"005", "8907134012", "Andreas", "Månsson", "0736423533", "Sturegatan 9D,211 50,Malmö", "Male"};
//            sqlConnector.editPatient(patientInfo);
//        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
