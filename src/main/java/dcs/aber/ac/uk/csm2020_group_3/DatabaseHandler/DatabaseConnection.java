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
        // Access data for the "STUDENT" table
        List<List<String>> studentTableData = tablesData.get("STUDENT");

        // Print all rows and columns in the "STUDENT" table
        for (List<String> row : studentTableData) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }

    }

}
