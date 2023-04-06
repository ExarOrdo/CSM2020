package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoreListGenerator {

    private DataLoader dataLoader;

    public CoreListGenerator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public List<ModuleInfo> generateCoreList(String studentID) {
        List<ModuleInfo> coreModules = new ArrayList<>();

        ResultSet coreModuleResult = dataLoader.loadModuleData(studentID);
        try {
            while (coreModuleResult.next()) {
                String moduleName = coreModuleResult.getString("ModuleName");
                int moduleCredits = coreModuleResult.getInt("ModuleCredits");
                int moduleSemester = coreModuleResult.getInt("ModuleSemester");
                coreModules.add(new ModuleInfo(moduleName, moduleCredits, moduleSemester));
            }
            coreModuleResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Core Modules: " + coreModules);
        return coreModules;
    }
}
