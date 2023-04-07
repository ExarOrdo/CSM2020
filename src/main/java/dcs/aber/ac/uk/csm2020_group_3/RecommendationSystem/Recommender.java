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
     * Lists containing moduleInfo objects per year.
     */

    public static ArrayList<Object> year1Modules;

    public static ArrayList<Object> year2Modules;

    public static ArrayList<Object> year3Modules;

    public static ArrayList<Object> year4Modules;


    private DataLoader dataLoader;

    /**
     * Constructor for Recommender class
     * @param studentID
     */
    public Recommender(String studentID) {
        dataLoader = new DataLoader();
        coreListGenerator = new CoreListGenerator(dataLoader);
        electiveListGenerator = new ElectiveListGenerator(dataLoader);
        weightGenerator = new WeightGenerator(coreListGenerator, electiveListGenerator);

        // calls methods after instantiating Recommender (after login/register)
        coreListGenerator.generateCoreList(studentID);
        electiveListGenerator.generateElectiveList(studentID);
    }

    public List<ModuleInfo> getCoreList() {
        return CoreListGenerator.coreModulesList;
    }

    public List<ModuleInfo> getElectiveList() {
        return ElectiveListGenerator.electiveModulesList;
    }

    public void getModuleData() throws SQLException {
        if (dataLoader.tryLoadingModules()) {
            System.out.println("Cores, optionals and module tables have been loaded!");
        } else {
            System.out.println("Table loading has failed!");
        }
    }
}
