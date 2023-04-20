// DatabaseHandler.java
package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseHandler {
    private final static String serverName = "agile-server.mysql.database.azure.com";
    private final static String databaseName = "AGILEDB";
    private final static String adminUsername = "GroupAdmin";
    private final static String adminPassword = "2675PKfe7$u!";

    protected static String connectionString = String.format("jdbc:mysql://%s:3306/%s?useSSL=true&requireSSL=false&user=%s&password=%s", serverName, databaseName, adminUsername, adminPassword);
    protected Connection connection;

    private static final Connection staticConnection = null;

    public static String currentStudentId;

    public static String getCurrentStudentId() {
        return currentStudentId;
    }

    public void load() {
    }

    public void save() {
    }

    public void remove() {
    }

    public void add() {
    }

    public void update() {
    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}
