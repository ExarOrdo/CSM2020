package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.sql.SQLException;
import java.util.List;

/**
 * Class responsible for handling the recommendation system,
 * uses several subclasses to distribute the workload
 */
public class Recommender {

    public static ResultSet coreTableResult;
    public static ResultSet optionalTableResult;
    public static ResultSet moduleTableResult;

    private ListUpdater listUpdater;

    private WeightGenerator weightGenerator;

    private StrengthCalculator strengthCalculator;

    private TimeLimitCalculator timeLimitCalculator;

    private CoreListGenerator coreListGenerator;

    private ElectiveListGenerator electiveListGenerator;

    /**
     * Dictionary of modules strings and weights -  {moduleName1:weight, ...., }
     */
    public static HashMap<String, Integer> currentModuleWeights;

    public static ArrayList<Object> currentlySelectedModules;

    private DataLoader dataLoader;

    public Recommender() {
        dataLoader = new DataLoader();
        coreListGenerator = new CoreListGenerator(dataLoader);
    }

    public List<ModuleInfo> loadModuleData(String studentID) {
        return coreListGenerator.generateCoreList(studentID);
    }



    public void getModuleData() throws SQLException {
        if (dataLoader.tryLoadingModules()) {
            System.out.println("Cores, optionals and module tables have been loaded!");
        } else {
            System.out.println("Table loading has failed!");
        }
    }
}
