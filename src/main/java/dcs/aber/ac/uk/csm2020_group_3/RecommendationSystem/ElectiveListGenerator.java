package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class parsing elective module data and storing them
 */

public class ElectiveListGenerator {

    public static List<ModuleInfo> electiveModulesList;

    private DataLoader dataLoader;

    public ElectiveListGenerator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void generateElectiveList(String studentID) {
        List<ModuleInfo> electiveModules = new ArrayList<>();

        ResultSet electiveModuleResult = dataLoader.loadModuleData(studentID);
        try {
            while (electiveModuleResult.next()) {
                String moduleName = electiveModuleResult.getString("ModuleName");
                int moduleCredits = electiveModuleResult.getInt("ModuleCredits");
                int moduleSemester = electiveModuleResult.getInt("ModuleSemester");
                String tag1 = electiveModuleResult.getString("ModuleName");
                String tag2 = electiveModuleResult.getString("ModuleName");
                String tag3 = electiveModuleResult.getString("ModuleName");
                electiveModules.add(new ModuleInfo(moduleName, moduleCredits, moduleSemester, tag1, tag2, tag3));
            }
            electiveModuleResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Elective Modules: " + electiveModules);
        electiveModulesList = electiveModules;
    }

}
