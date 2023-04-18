package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class parsing elective module data and storing them
 */

public class ElectiveListGenerator extends ModuleHandler{

    public static ArrayList<Module> electiveModulesList;
    //

    private DataLoader dataLoader;

    public ElectiveListGenerator(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void generateElectiveList(String studentID) {
        ArrayList<Module> electiveModules = new ArrayList<>();
        String tag2 = null;
        String tag3 = null;
        String tag4 = null;
        String tag5 = null;
        String tag6 = null;
        String tag7 = null;
        String tag8 = null;
        String prerequisiteModule = "";

        ResultSet electiveModuleResult = dataLoader.loadModuleData(studentID);
        try {
            while (electiveModuleResult.next()) {
                String moduleName = electiveModuleResult.getString("ModuleName");
                int moduleCredits = electiveModuleResult.getInt("ModuleCredits");
                int moduleSemester = electiveModuleResult.getInt("ModuleSemester");
                int moduleYear = electiveModuleResult.getInt("ModuleYear");
                String tag1 = electiveModuleResult.getString("ModuleTag1");
                Module electiveModule = new Module(moduleName, moduleCredits, moduleSemester, moduleYear, tag1);

                try {
                    tag2 = electiveModuleResult.getString("ModuleTag2");
                    if (tag2 != null) {
                        electiveModule.setModuleTag(tag2, 2);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag3 = electiveModuleResult.getString("ModuleTag3");
                    if (tag3 != null) {
                        electiveModule.setModuleTag(tag3, 3);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag4 = electiveModuleResult.getString("ModuleTag4");
                    if (tag4 != null) {
                        electiveModule.setModuleTag(tag4, 4);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag5 = electiveModuleResult.getString("ModuleTag5");
                    if (tag5 != null) {
                        electiveModule.setModuleTag(tag5, 5);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag6 = electiveModuleResult.getString("ModuleTag6");
                    if (tag6 != null) {
                        electiveModule.setModuleTag(tag6, 6);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag7 = electiveModuleResult.getString("ModuleTag7");
                    if (tag7 != null) {
                        electiveModule.setModuleTag(tag7, 7);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try {
                    tag8 = electiveModuleResult.getString("ModuleTag8");
                    if (tag8 != null) {
                        electiveModule.setModuleTag(tag8, 8);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                try{
                    prerequisiteModule = electiveModuleResult.getString("ModulePrerequisite");
                    if (prerequisiteModule != null) {
                        electiveModule.setPrerequisite(prerequisiteModule);
                    }
                } catch (SQLException e) {
                    //e.printStackTrace();
                }

                electiveModules.add(electiveModule);
            }
            electiveModuleResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Elective Modules: " + electiveModules);
        electiveModulesList = electiveModules;
    }

}
