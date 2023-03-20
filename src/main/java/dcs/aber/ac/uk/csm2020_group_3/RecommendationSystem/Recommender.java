package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.sql.SQLException;

/**
 * Class responsible for handling the recommendation system,
 * uses several subclasses to distribute the workload
 */
public class Recommender {

    private ListUpdater listUpdater;

    private StrengthCalculator strengthCalculator;

    private TimeLimitCalculator timeLimitCalculator;

    private CoreListGenerator coreListGenerator;

    private ElectiveListGenerator electiveListGenerator;

    private DataLoader dataLoader;


    public Recommender(){
        dataLoader = new DataLoader();
    }

    public void getModuleData() throws SQLException {
        if (dataLoader.tryLoadingModules()) {
            System.out.println("Cores, optionals and module tables have been loaded!");
        } else {
            System.out.println("Table loading has failed!");
        }
    }
}
