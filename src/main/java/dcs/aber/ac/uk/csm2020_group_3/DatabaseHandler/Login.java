package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import javafx.scene.Scene;

import java.sql.*;
import java.util.Scanner;
/**
 * Class used for logging in, checks login credentials in the db
 */
public class Login extends DatabaseHandler {



    private String username;
    private String password;

    public boolean Login(String username, String password){
        this.username = username;
        this.password = password;
        try{

            String serverName = "agile-server.database.windows.net";
            String databaseName = "AGILEDB";
            String adminUsername = "GroupAdmin";
            String adminPassword = "2675PKfe7$u!";

            String connectionString = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;loginTimeout=30;", serverName, databaseName, adminUsername, adminPassword);
            Connection connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM STUDENT WHERE StudentID = '" + username + "' AND StudentPassword = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
              if (resultSet.next()){
                  return true;
              }

              resultSet.close();
              statement.close();
              connection.close();


        }catch  (Exception err) {
            System.out.println("Error:" + err.getMessage());
        }
        return false;
    }

    @Override
    public void load() {;}

}
