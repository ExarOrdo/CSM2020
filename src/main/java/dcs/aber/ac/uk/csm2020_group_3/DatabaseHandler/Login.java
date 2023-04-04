package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.*;

/**
 * Class used for logging in, checks login credentials in the db
 */
public class Login {

    private final String studentId;
    private final String password;

    public Login(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public boolean tryLogin() {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM STUDENT WHERE StudentID = '" + this.studentId + "' AND StudentPassword = '" + this.password + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return true;
            }

            resultSet.close();
            statement.close();
        } catch (Exception err) {
            System.err.println("Error:" + err.getMessage());
            err.printStackTrace();
        }
        return false;
    }

}