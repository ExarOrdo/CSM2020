package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;
import java.sql.*;

/**
 * Class used for logging in, checks login credentials in the db
 */
public class Login extends DatabaseHandler {

    private final String username;
    private final String password;

    public Login (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean tryLogin() throws SQLException{
        this.connection = DriverManager.getConnection(connectionString);

        try{

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM STUDENT WHERE StudentID = '" + this.username + "' AND StudentPassword = '" + this.password + "'";
            ResultSet resultSet = statement.executeQuery(query);

              if (resultSet.next()){
                  return true;
              }

              resultSet.close();
              statement.close();
              connection.close();


        }catch  (Exception err) {
            System.err.println("Error:" + err.getMessage());
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public void load() {;}

}
