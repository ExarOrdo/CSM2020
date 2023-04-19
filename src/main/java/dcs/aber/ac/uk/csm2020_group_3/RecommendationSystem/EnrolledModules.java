package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that provides functions for Modules stored in the DB
 */
public class EnrolledModules extends ModuleHandler {

    public static ArrayList<String> enrolledModuleList;
    private final DataLoader dataLoader;

    public EnrolledModules(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void generateEnrolledModules(String studentID) throws SQLException {
        ArrayList<String> enrolledModules = new ArrayList<>();

        ResultSet enrolledModuleResult = dataLoader.loadModulesNoMarks(studentID);
        try {
            while (enrolledModuleResult.next()) {
                //String moduleName = enrolledModuleResult.getString("ModuleName");
                String moduleID = enrolledModuleResult.getString("ModuleID");

                //Module electiveModule = new Module(moduleName, moduleCredits, moduleSemester, moduleYear, tag1);

                enrolledModules.add(moduleID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enrolled Modules: " + enrolledModules);
        enrolledModuleList = enrolledModules;
    }
}
