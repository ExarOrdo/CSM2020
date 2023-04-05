package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLoader extends DatabaseHandler {

    private CheckEnrolledModules checkEnrolledModules;

    private final String courseQuery = "SELECT StudentCourse FROM STUDENT WHERE StudentID = ?";
    private final String coreModulesQuery = "SELECT ModuleName FROM CORE_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?";
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

    public static class ModuleInfo {
        private String moduleName;
        private int moduleCredits;
        private int moduleSemester;

        public ModuleInfo(String moduleName, int moduleCredits, int moduleSemester) {
            this.moduleName = moduleName;
            this.moduleCredits = moduleCredits;
            this.moduleSemester = moduleSemester;
        }

        public String getModuleName() {
            return moduleName;
        }

        public int getModuleCredits() {
            return moduleCredits;
        }

        public int getModuleSemester() {
            return moduleSemester;
        }
    }

    public List<ModuleInfo> loadModuleData(String studentID) {
        List<ModuleInfo> coreModules = new ArrayList<>();

        try {
            Connection connection = getConnection();

            // Find the course name for the student
            String courseID = "";
            PreparedStatement studentStatement = connection.prepareStatement("SELECT StudentCourse FROM STUDENT WHERE StudentID = ?");
            studentStatement.setString(1, studentID);
            ResultSet studentResult = studentStatement.executeQuery();
            if (studentResult.next()) {
                courseID = studentResult.getString("StudentCourse");
            }
            System.out.println("CourseID: " + courseID); // Debug statement

            if (!courseID.isEmpty()) {
                // Find the core modules for the course
                PreparedStatement coreModuleStatement = connection.prepareStatement("SELECT m.ModuleName, m.ModuleCredits, m.ModuleSemester FROM CORE_MODULE cm JOIN MODULE m ON cm.ModuleID = m.ModuleID WHERE cm.CourseID = ?");
                coreModuleStatement.setString(1, courseID);
                ResultSet coreModuleResult = coreModuleStatement.executeQuery();
                while (coreModuleResult.next()) {
                    String moduleName = coreModuleResult.getString("ModuleName");
                    int moduleCredits = coreModuleResult.getInt("ModuleCredits");
                    int moduleSemester = coreModuleResult.getInt("ModuleSemester");
                    coreModules.add(new ModuleInfo(moduleName, moduleCredits, moduleSemester));
                }

                // Close the connection
                coreModuleResult.close();
                coreModuleStatement.close();
            }

            studentResult.close();
            studentStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Core Modules: " + coreModules); // Debug statement
        return coreModules;
    }

}
