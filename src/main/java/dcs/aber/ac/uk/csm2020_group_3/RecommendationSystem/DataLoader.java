package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataLoader extends DatabaseHandler {

    private CheckEnrolledModules checkEnrolledModules;

    private final String coreModulesQuery = "SELECT * FROM CORE_MODULE";
    private final String optionalModulesQuery = "SELECT * FROM OPTIONAL_MODULE";
    private final String modulesQuery = "SELECT * FROM MODULE";

    public boolean tryLoadingModules() throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);

        try {
            Statement statement = connection.createStatement();

            ResultSet coreTableResult = statement.executeQuery(coreModulesQuery);
            if (!coreTableResult.next()) {
                System.err.println("Core modules failed to load!");
                return false;
            }
            //load the core table into local structure

            ResultSet optionalTableResult = statement.executeQuery(optionalModulesQuery);
            if (!optionalTableResult.next()) {
                System.err.println("Optional modules failed to load!");
                return false;
            }
            //load optionals into local structure

            ResultSet moduleTableResult = statement.executeQuery(modulesQuery);
            if (!moduleTableResult.next()) {
                System.err.println("Modules table failed to load!");
                return false;
            }
            //load modules table into local

            statement.close();
            connection.close();

            return true;

        } catch (Exception err) {
            System.err.println("Error: " + err.getMessage());
            err.printStackTrace();
        }

        return false;
    }
}
