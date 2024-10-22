package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private final String saveModulesString = "INSERT INTO marks (StudentID, ModuleID, StudentMark, MarkDate) VALUES (?, ?, ?, ?)";
    private final String isModuleinRecordString = "SELECT * FROM marks WHERE StudentID = ? AND ModuleID = ?";
    private final String getModuleIDByNameString = "SELECT ModuleID FROM MODULE WHERE ModuleName = ?";
    private final String deleteDuplicates = "DELETE FROM marks WHERE StudentID = ? AND ModuleID = ?";
    private final String resetModuleChoices = "DELETE FROM Marks WHERE StudentID = ? AND StudentMark = -1 AND MarkDate IS NULL";

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

    public void resetOptionalModules(String studentID) {

        try (Connection connection = DatabaseHandler.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(resetModuleChoices)) {
            preparedStatement.setString(1, studentID);
            int deletedRows = preparedStatement.executeUpdate();
            System.out.println(deletedRows + " rows deleted from Marks table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isModuleinRecord(String studentID, String moduleID) {
        boolean exists = false;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(isModuleinRecordString);
            statement.setString(1, studentID);
            statement.setString(2, moduleID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }


    public String getModuleIDByName(String moduleName) {
        String moduleID = "";

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(getModuleIDByNameString);
            statement.setString(1, moduleName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                moduleID = resultSet.getString("ModuleID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moduleID;
    }

    public List<String> saveSelectedModules(String studentID, List<String> selectedOptionalModuleNames) {
        List<String> alreadyExistingModules = new ArrayList<>();
        List<String> insertedModuleIds = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(saveModulesString)) {

            for (String moduleName : selectedOptionalModuleNames) {
                String moduleId = getModuleIDByName(moduleName);

                if (!moduleId.isEmpty()) {
                    if (isModuleinRecord(studentID, moduleId)) {
                        alreadyExistingModules.add(moduleName);
                    } else {
                        preparedStatement.setString(1, studentID);
                        preparedStatement.setString(2, moduleId);
                        preparedStatement.setInt(3, -1);
                        preparedStatement.setNull(4, java.sql.Types.DATE);
                        preparedStatement.executeUpdate();
                        insertedModuleIds.add(moduleId);
                    }
                } else {
                    System.err.println("Module not found for name: " + moduleName);
                }
            }

            if (!alreadyExistingModules.isEmpty()) {
                PreparedStatement deleteStatement = connection.prepareStatement(deleteDuplicates);
                for (String moduleId : insertedModuleIds) {
                    deleteStatement.setString(1, studentID);
                    deleteStatement.setString(2, moduleId);
                    deleteStatement.executeUpdate();
                }
                deleteStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alreadyExistingModules;
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
                studentDetails.put("student_year", resultSet.getString("StudentYear"));
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

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT marks.ModuleID FROM marks JOIN STUDENT ON STUDENT.StudentID JOIN MODULE ON MODULE.ModuleID = MARKS.ModuleID WHERE marks.StudentID = ? AND marks.StudentMark = -1 AND STUDENT.StudentYear = MODULE.ModuleYear");
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

    public int getStudentYearById(String studentId) throws SQLException {
        this.connection = getConnection();
        int year = -1;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT StudentYear FROM STUDENT WHERE StudentID = ?");
            statement.setString(1, studentId);

            ResultSet yearSet = statement.executeQuery();

            yearSet.next();
            year = yearSet.getInt("StudentYear");

        } catch (Exception err) {
            System.err.println("Error when getting student year for timetable: " + err.getMessage());
            err.printStackTrace();
        }
        return year;
    }

}
