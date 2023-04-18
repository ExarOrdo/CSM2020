package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordLoader extends DatabaseHandler{

    public int getStudentYear(String studentId) throws SQLException {

        this.connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT StudentYear FROM STUDENT WHERE StudentID = ?");
            statement.setString(1, studentId);

            ResultSet studentYear = statement.executeQuery();

            studentYear.next();
            return studentYear.getInt("StudentYear");

        } catch (Exception err) {
            System.err.println("Error during getting student year: " + err.getMessage());
            err.printStackTrace();
        }
        return -1;
    }
}
