package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.*;
import java.util.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        Map<String, List<List<String>>> tablesData = null;
        try {
            // Create a connection to the database using Active Directory Password Authentication
            String serverName = "agile-server.database.windows.net";
            String databaseName = "AGILEDB";
            String username = "GroupAdmin";
            String password = "2675PKfe7$u!";

            String connectionString = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;loginTimeout=30;", serverName, databaseName, username, password);
            Connection connection = DriverManager.getConnection(connectionString);

            // Get a list of all tables in the database
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet tables = metadata.getTables(null, null, null, new String[]{"TABLE"});

            // Loop through all tables and select all rows
            tablesData = new HashMap<>();
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (!tableName.equals("trace_xe_event_map") && !tableName.equals("trace_xe_action_map")) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
                    List<List<String>> tableRows = new ArrayList<>();
                    while (resultSet.next()) {
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        List<String> rowData = new ArrayList<>();
                        for (int i = 1; i <= columnCount; i++) {
                            String column = resultSet.getString(i);
                            rowData.add(column);
                        }
                        tableRows.add(rowData);
                    }
                    tablesData.put(tableName, tableRows);
                    resultSet.close();
                    statement.close();
                }
            }


            // Close the connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print out the core modules for a specific student
        List<List<String>> studentCoreModules = new ArrayList<>();

        // Access data for the "STUDENT" and "CORE_MODULE" tables
        List<List<String>> studentTableData = tablesData.get("STUDENT");
        List<List<String>> coreModuleTableData = tablesData.get("CORE_MODULE");

        // Get the student ID of interest
        String studentID = "12345";

        // Find the course ID and year for the student
        String courseID = "";
        String year = "";
        for (List<String> row : studentTableData) {
            if (row.get(0).equals(studentID)) {
                courseID = row.get(2);
                year = row.get(3);
                break;
            }
        }

        // Find the core modules for the course and year
        for (List<String> row : coreModuleTableData) {
            if (row.get(0).equals(courseID)) {
                String moduleID = row.get(1);
                for (List<String> moduleRow : tablesData.get("MODULE")) {
                    if (moduleRow.get(0).equals(moduleID) && moduleRow.get(4).equals(year)) {
                        studentCoreModules.add(moduleRow);
                        break;
                    }
                }
            }
        }

        // Print out the core modules
        System.out.println("Core modules for student " + studentID + ":");
        for (List<String> row : studentCoreModules) {
            System.out.println(row.get(1)); // Print the module name
        }

    }

}