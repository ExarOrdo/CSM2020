package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An abstract class that will be inherited by various
 * classes that interact with the database, but
 * will need to perform different actions based
 * on their goal.
 */
public abstract class DatabaseHandler {
    private final static String serverName = "agile-server.mysql.database.azure.com";
    private final static String databaseName = "AGILEDB";
    private final static String adminUsername = "GroupAdmin";
    private final static String adminPassword = "2675PKfe7$u!";

    protected String connectionString = String.format("jdbc:mysql://%s:3306/%s?useSSL=true&requireSSL=false&user=%s&password=%s", serverName, databaseName, adminUsername, adminPassword);
    protected Connection connection;

    public String currentStudent;

    /**
     * method for loading specific information from the db
     */
    public void load(){;}

    /**
     * Method for saving specific information to the db
     */
    public void save() {;}

    /**
     * Method for removing specified data from the db
     */
    public void remove() {;}

    /**
     * Method for adding data into specific place in the db
     */
    public void add() {;}

    /**
     * Method for editing already existing data in the db
     */
    public void update() {;}

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.connectionString);
    }

}
