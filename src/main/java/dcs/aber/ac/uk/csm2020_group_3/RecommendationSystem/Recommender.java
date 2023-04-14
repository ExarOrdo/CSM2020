package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * Class responsible for handling the recommendation system,
 * uses several subclasses to distribute the workload
 */
public class Recommender extends ModuleHandler{

    public static ResultSet coreTableResult;
    public static ResultSet optionalTableResult;
    public static ResultSet moduleTableResult;

    private ListUpdater listUpdater;

    private WeightGenerator weightGenerator;

    private StrengthCalculator strengthCalculator;

    private TimeLimitCalculator timeLimitCalculator;

    private CoreListGenerator coreListGenerator;

    private ElectiveListGenerator electiveListGenerator;

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
        strengthCalculator = new StrengthCalculator();

        // calls methods after instantiating Recommender (after login/register)
        coreListGenerator.generateCoreList(studentID);
        electiveListGenerator.generateElectiveList(studentID);
        sortModulesAndCheckCredits(coreListGenerator.getCoreModulesList());
        weightGenerator.generateWeights(this.getCoreList(), this.getElectiveList());
        strengthCalculator.sortByWeights(this.getElectiveList());
    }

    public ArrayList<Module> getCoreList() {
        return CoreListGenerator.coreModulesList;
    }

    public ArrayList<Module> getElectiveList() {
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
