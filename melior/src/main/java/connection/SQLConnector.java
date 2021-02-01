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

    public static void main(String[] args) {
        SQLConnector sqlConnector = new SQLConnector();

        List<Object[]> test = new ArrayList<>();

        Object[] dataOne = {"Foot doctor", "100"};
        Object[] dataTwo = {"Cardiologist", "300"};

        test.add(dataOne);
        test.add(dataTwo);


        try {
            sqlConnector.addSpecs(test);

        } catch(SQLException | UnknownHostException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
