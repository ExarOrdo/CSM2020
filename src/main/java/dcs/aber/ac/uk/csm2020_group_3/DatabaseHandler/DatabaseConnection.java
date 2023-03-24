package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private static Connection connection = null;
    private static final String serverName = "agile-server.mysql.database.azure.com";
    private static final String databaseName = "AGILEDB";
    private static final String username = "GroupAdmin";
    private static final String password = "2675PKfe7$u!";

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String connectionString = String.format("jdbc:mysql://%s:3306/%s?useSSL=true&requireSSL=false&user=%s&password=%s", serverName, databaseName, username, password);
            connection = DriverManager.getConnection(connectionString);
        }
        return connection;
    }

    public static List<String> getCoreModulesForStudent(String studentID) {
        List<String> coreModules = new ArrayList<>();

        try {
            Connection connection = getConnection();

            // Find the course name for the student
            String courseID = "";
            PreparedStatement studentStatement = connection.prepareStatement("SELECT StudentCourse FROM STUDENT WHERE StudentID = ?");
            studentStatement.setString(1, studentID);
            ResultSet studentResult = studentStatement.executeQuery();
            if (studentResult.next()) {
                courseID = studentResult.getString("StudentCourse");
            }

            // Find the core modules for the course
            PreparedStatement coreModuleStatement = connection.prepareStatement("SELECT ModuleName FROM CORE_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?");
            coreModuleStatement.setString(1, courseID);
            ResultSet coreModuleResult = coreModuleStatement.executeQuery();
            while (coreModuleResult.next()) {
                String moduleName = coreModuleResult.getString("ModuleName");
                coreModules.add(moduleName);
            }

            // Close the connection
            coreModuleResult.close();
            coreModuleStatement.close();
            studentResult.close();
            studentStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coreModules;
    }

}