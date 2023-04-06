package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;

import java.sql.*;
import java.sql.ResultSet;

public class DataLoader extends DatabaseHandler {

    private CheckEnrolledModules checkEnrolledModules;

    private final String courseQuery = "SELECT StudentCourse FROM STUDENT WHERE StudentID = ?";
    private final String coreModulesQuery = "SELECT m.ModuleName, m.ModuleCredits, m.ModuleSemester FROM CORE_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?";
    private final String optionalModulesQuery = "SELECT * FROM OPTIONAL_MODULE";
    private final String modulesQuery = "SELECT * FROM MODULE";


    public boolean tryLoadingModules() throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);

        try {
            Statement statement = connection.createStatement();

            ResultSet studentCourseResult = statement.executeQuery(courseQuery);
            if (!studentCourseResult.next()) {
                System.err.println("Student course failed to load!");
                return false;
            }
            //load into local structure

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


        } catch (Exception err) {
            System.err.println("Error: " + err.getMessage());
            err.printStackTrace();
        }

        return false;
    }


    public ResultSet loadModuleData(String studentID) {
        ResultSet coreModuleResult = null;
        try {
            Connection connection = getConnection();

            // Find the course name for the student
            String courseID = "";
            PreparedStatement studentStatement = connection.prepareStatement(courseQuery);
            studentStatement.setString(1, studentID);
            ResultSet studentResult = studentStatement.executeQuery();
            if (studentResult.next()) {
                courseID = studentResult.getString("StudentCourse");
            }
            System.out.println("CourseID: " + courseID); // Debug statement

            if (!courseID.isEmpty()) {
                // Find the core modules for the course
                PreparedStatement coreModuleStatement = connection.prepareStatement(coreModulesQuery);
                coreModuleStatement.setString(1, courseID);
                coreModuleResult = coreModuleStatement.executeQuery();
            }

            studentResult.close();
            studentStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coreModuleResult;
    }

}
