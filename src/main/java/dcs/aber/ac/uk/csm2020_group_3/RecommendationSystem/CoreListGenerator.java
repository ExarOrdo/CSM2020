package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class parsing core module data and storing them
 */
public class CoreListGenerator extends ModuleHandler {

    public static ArrayList<Module> coreModulesList;
    private DataLoader dataLoader;

    public CoreListGenerator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    public ArrayList<Module> getCoreModulesList(){
        return coreModulesList;
    }

    /**
     * Creates a list of Module objects.
     * These are immediately sorted by the ModuleHandler
     */
    public void generateCoreList(String studentID) {
        ArrayList<Module> coreModules = new ArrayList<>(30);
        String tag2 = null;
        String tag3 = null;
        String tag4 = null;
        String tag5 = null;
        String tag6 = null;
        String tag7 = null;
        String tag8 = null;

        ResultSet coreModuleResult = dataLoader.loadModuleData(studentID);
        try {
            while (coreModuleResult.next()) {
                String moduleName = coreModuleResult.getString("ModuleName");
                int moduleCredits = coreModuleResult.getInt("ModuleCredits");
                int moduleSemester = coreModuleResult.getInt("ModuleSemester");
                int moduleYear = coreModuleResult.getInt("ModuleYear");
                String tag1 = coreModuleResult.getString("ModuleTag1");
                Module coreModule = new Module(moduleName, moduleCredits, moduleSemester, moduleYear, tag1);

                try {
                    tag2 = coreModuleResult.getString("ModuleTag2");
                    if (tag2 != null) {
                        coreModule.setModuleTag(tag2, 2);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag3 = coreModuleResult.getString("ModuleTag3");
                    if (tag3 != null) {
                        coreModule.setModuleTag(tag3, 3);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag4 = coreModuleResult.getString("ModuleTag4");
                    if (tag4 != null) {
                        coreModule.setModuleTag(tag4, 4);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag5 = coreModuleResult.getString("ModuleTag5");
                    if (tag5 != null) {
                        coreModule.setModuleTag(tag5, 5);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag6 = coreModuleResult.getString("ModuleTag6");
                    if (tag6 != null) {
                        coreModule.setModuleTag(tag6, 6);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag7 = coreModuleResult.getString("ModuleTag7");
                    if (tag7 != null) {
                        coreModule.setModuleTag(tag7, 7);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag8 = coreModuleResult.getString("ModuleTag8");
                    if (tag8 != null) {
                        coreModule.setModuleTag(tag8, 8);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                coreModules.add(coreModule);
            }
            coreModuleResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Core Modules: " + coreModules);
        coreModulesList = coreModules;

    }

}
