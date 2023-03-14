package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        try {
            // Create a connection to the database using Active Directory Password Authentication
            String serverName = "agile-server.database.windows.net";
            String databaseName = "AGILEDB";
            String username = "GroupAdmin";
            String password = "2675PKfe7$u!";

            //String connectionString = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;loginTimeout=30;authentication=ActiveDirectoryPassword;", serverName, databaseName, username, password);
            String connectionString = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;loginTimeout=30;", serverName, databaseName, username, password);
            Connection connection = DriverManager.getConnection(connectionString);

            // Execute a basic query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");

            while (resultSet.next()) {
                String column1 = resultSet.getString("StudentName");
                String column2 = resultSet.getString("StudentCourse");
                String column3 = resultSet.getString("StudentYear");
                String column4 = resultSet.getString("StudentPassword");
                String column5 = resultSet.getString("StudentID");
                System.out.println(column1);
                System.out.println(column2);
                System.out.println(column3);
                System.out.println(column4);
                System.out.println(column5);

                // process the results
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}