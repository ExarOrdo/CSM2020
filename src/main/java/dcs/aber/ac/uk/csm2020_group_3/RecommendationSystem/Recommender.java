package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Class responsible for handling the recommendation system,
 * uses several subclasses to distribute the workload
 */
public class Recommender extends ModuleHandler {

    public static ResultSet coreTableResult;
    public static ResultSet optionalTableResult;
    public static ResultSet moduleTableResult;


    public WeightGenerator weightGenerator;

    public StrengthCalculator strengthCalculator;


    private final CoreListGenerator coreListGenerator;

    private final ElectiveListGenerator electiveListGenerator;

    private final DataLoader dataLoader;

    /**
     * Constructor for Recommender class
     *
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
        sortModules(coreListGenerator.getCoreModulesList());
        weightGenerator.generateWeights(this.getCoreList(), this.getElectiveList());
        strengthCalculator.sortByWeights(this.getElectiveList());

    }

    public void recalculateWeightsOnAdd() {
        // recalculate weights with chosen electives and regenerate lists
        weightGenerator.recalculateWeightsOnAdd();
        strengthCalculator.sortByWeights(ElectiveListGenerator.electiveModulesList);
    }

    public void recalculateWeightsOnRemove() {
        // recalculate weights with chosen electives and regenerate lists
        weightGenerator.recalculateWeightsOnRemove();
        strengthCalculator.sortByWeights(ElectiveListGenerator.electiveModulesList);
    }

    public ArrayList<Module> getCoreList() {
        return CoreListGenerator.coreModulesList;
    }

    public ArrayList<Module> getElectiveList() {
        return ElectiveListGenerator.electiveModulesList;
    }


}
