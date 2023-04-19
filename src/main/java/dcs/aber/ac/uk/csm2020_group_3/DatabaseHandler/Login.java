package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;


import dcs.aber.ac.uk.csm2020_group_3.Main;

import java.sql.*;
import java.util.Objects;

/**
 * Class used for logging in, checks login credentials in the db
 */

public class Login extends DatabaseHandler {

    private final String studentId;
    private final String password;

    public static void setCurrentStudentId(String studentId) {
        currentStudentId = studentId;
    }

    public Login(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public boolean tryLogin() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            System.out.println(Main.currentStage.getTitle());
            if (Objects.equals(Main.currentStage.getTitle(), "AdminLogin")){
                String query = "SELECT * FROM admin WHERE adminId = '" + this.studentId + "' AND adminPassword = '" + this.password + "'";
                resultSet = statement.executeQuery(query);

            }
            else {
                String query = "SELECT * FROM STUDENT WHERE StudentID = '" + this.studentId + "' AND StudentPassword = '" + this.password + "'";
                resultSet = statement.executeQuery(query);
            }

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