package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

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


    public Recommender(){;}
}
