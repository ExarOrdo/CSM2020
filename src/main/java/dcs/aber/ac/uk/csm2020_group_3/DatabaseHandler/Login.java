package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;
import java.sql.*;

/**
 * Class used for logging in, checks login credentials in the db
 */
public class Login extends DatabaseHandler {

    private final String studentId;
    private final String password;

    public Login (String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
        currentStudent = studentId;
    }


    public boolean checkExisting() throws SQLException{
        this.connection = DriverManager.getConnection(connectionString);

        return false;
    }
    public boolean tryLogin() throws SQLException{
        this.connection = DriverManager.getConnection(connectionString);

        try{

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM STUDENT WHERE StudentID = '" + this.studentId + "' AND StudentPassword = '" + this.password + "'";
            ResultSet resultSet = statement.executeQuery(query);

              if (resultSet.next()){
                  System.out.println(currentStudent);
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
