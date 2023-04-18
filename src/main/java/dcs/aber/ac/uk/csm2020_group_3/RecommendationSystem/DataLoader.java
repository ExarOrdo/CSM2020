package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;

import java.sql.*;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DataLoader extends DatabaseHandler {

    private EnrolledModules checkEnrolledModules;

    //

    private final String courseQuery = "SELECT StudentCourse FROM STUDENT WHERE StudentID = ?";
    private final String coreModulesQuery = "SELECT m.ModuleName, m.ModuleCredits, m.ModuleSemester, m.ModuleYear, m.ModuleTag1, m.ModuleTag2, m.ModuleTag3, m.ModuleTag4, m.ModuleTag5, m.ModuleTag6, m.ModuleTag7, m.ModuleTag8 FROM CORE_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?";
    private final String electiveModulesQuery = "SELECT m.ModuleName, m.ModuleCredits, m.ModuleSemester, m.ModuleYear, m.ModuleTag1, m.ModuleTag2, m.ModuleTag3, m.ModuleTag4, m.ModuleTag5, m.ModuleTag6, m.ModuleTag7, m.ModuleTag8 FROM OPTIONAL_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?";
    private final String modulesQuery = "SELECT * FROM MODULE";
    private final String studentRecordQuery = "SELECT * FROM STUDENT WHERE StudentID = ?";
    private final String studentRecordModulesQuery = "SELECT module.ModuleID, marks.StudentMark, module.ModuleYear, marks.MarkDate FROM marks JOIN module ON marks.ModuleID = module.ModuleID WHERE marks.StudentID = ? AND module.ModuleYear = ?";
    private final String moduleNameAndDescriptionQuery = "SELECT ModuleName, ModuleDescription FROM MODULE WHERE ModuleID = ?";

   // private final String confirmedStudentModules = "SELECT marks.ModuleID, marks.StudentMark, module.ModuleName FROM marks JOIN module ON marks.ModuleID = module.ModuleID WHERE marks.StudentID = ? AND marks.StudentMark = null";

    private final String confirmedStudentModules = "SELECT marks.StudentID, marks.ModuleID, marks.StudentMark FROM marks WHERE marks.StudentID = ? AND marks.StudentMark = -1";

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

            ResultSet optionalTableResult = statement.executeQuery(electiveModulesQuery);
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


    public Map<String, String> loadStudentDetails(String studentId) {
        Map<String, String> studentDetails = new HashMap<>();

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(studentRecordQuery);
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                studentDetails.put("student_id", resultSet.getString("StudentID"));
                studentDetails.put("student_name", resultSet.getString("StudentName"));
                studentDetails.put("student_course", resultSet.getString("StudentCourse"));
                studentDetails.put("student_year", resultSet.getString("StudentYear")); // Added student_year
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentDetails;
    }

    public ResultSet loadStudentModulesWithMarksByYear(String studentID, int year) {

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(studentRecordModulesQuery);
            preparedStatement.setString(1, studentID);
            preparedStatement.setInt(2, year);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet loadModulesNoMarks(String studentID) throws SQLException {
        connection = getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT marks.ModuleID FROM marks WHERE marks.StudentID = ? AND marks.StudentMark = -1");
            preparedStatement.setString(1, studentID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Map<String, String> getModuleNameAndDescription(String moduleID) {
        Map<String, String> moduleNameAndDescription = new HashMap<>();

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(moduleNameAndDescriptionQuery);
            statement.setString(1, moduleID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                moduleNameAndDescription.put("module_name", resultSet.getString("ModuleName"));
                moduleNameAndDescription.put("module_description", resultSet.getString("ModuleDescription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moduleNameAndDescription;
    }


    public ResultSet loadModuleData(String studentID) {
        ResultSet coreModuleResult = null;
        ResultSet electiveModuleResult = null;
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

                // Find electives
                // PreparedStatement electiveModuleStatement = connection.prepareStatement(electiveModulesQuery);
                // electiveModuleStatement.setString(1, courseID);
                // electiveModuleResult = coreModuleStatement.executeQuery();
            }

            studentResult.close();
            studentStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coreModuleResult;
    }

    public ResultSet loadModuleDataElective(String studentID) {
        ResultSet coreModuleResult = null;
        ResultSet electiveModuleResult = null;
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
                PreparedStatement electiveModuleStatement = connection.prepareStatement(electiveModulesQuery);
                electiveModuleStatement.setString(1, courseID);
                electiveModuleResult = electiveModuleStatement.executeQuery();

                // Find electives
                // PreparedStatement electiveModuleStatement = connection.prepareStatement(electiveModulesQuery);
                // electiveModuleStatement.setString(1, courseID);
                // electiveModuleResult = coreModuleStatement.executeQuery();
            }

            studentResult.close();
            studentStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return electiveModuleResult;
    }

}
