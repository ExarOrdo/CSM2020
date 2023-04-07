package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class parsing core module data and storing them
 */
public class CoreListGenerator {

    public static List<ModuleInfo> coreModulesList;

    private DataLoader dataLoader;

    /**
     * Creates a list of ModuleInfo objects
     */
    public CoreListGenerator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void generateCoreList(String studentID) {
        List<ModuleInfo> coreModules = new ArrayList<>();

        ResultSet coreModuleResult = dataLoader.loadModuleData(studentID);
        try {
            while (coreModuleResult.next()) {
                String moduleName = coreModuleResult.getString("ModuleName");
                int moduleCredits = coreModuleResult.getInt("ModuleCredits");
                int moduleSemester = coreModuleResult.getInt("ModuleSemester");
                String tag1 = coreModuleResult.getString("ModuleName");
                String tag2 = coreModuleResult.getString("ModuleName");
                String tag3 = coreModuleResult.getString("ModuleName");
                coreModules.add(new ModuleInfo(moduleName, moduleCredits, moduleSemester, tag1, tag2, tag3));
            }
            coreModuleResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Core Modules: " + coreModules);
        coreModulesList = coreModules;

    }
}
