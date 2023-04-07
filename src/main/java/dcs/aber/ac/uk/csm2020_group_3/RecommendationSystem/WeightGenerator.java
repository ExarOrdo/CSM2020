package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class responsible for generating and calculating similarity between modules (weights)
 * Gets tags from ResultSet objects from the DataLoader.
 */
public class WeightGenerator {

    private ElectiveListGenerator electiveListGenerator;

    private CoreListGenerator coreListGenerator;

    public WeightGenerator (CoreListGenerator coreListGenerator, ElectiveListGenerator electiveListGenerator) {
        this.coreListGenerator = coreListGenerator;
        this.electiveListGenerator = electiveListGenerator;
    }

    /**
     * Method that gets tags from ModuleInfo Objects - might not be needed.
     */
    //public ArrayList<String> tagExtractor(){
    //   return
    //}

    /**
     * Calculates Jaccard's index between two lists of tags
     */
    private float calculateWeight(ArrayList<String> list1, ArrayList<String> list2) {
        // get size of list1, list2
        int size1 = list1.size();
        int size2 = list2.size();

        // declare variables
        int count = 0;
        float weight = 0;

        // count number of matching tags in both lists of tags
        for (String s1 : list1) {
            for (String s2 : list2) {
                if (s1.equals(s2)) {
                    count++;
                }
            }
        }

        // Calculate metric
        weight = (float) count / (size1 + size2 - count);

        return weight;
    }


    /**
     * Method that generates weights between each elective module and each core module, then
     * combines those to create a weight metric for each elective module.
     * Returns a hashTable - moduleName: weight
     */
    public static void generateWeights() {

        // access current electives
        //electiveListGenerator()

        // access current cores

        // run for loop with jaccards
        // add values to dict
        }
       ;

    /**
     * Method that is called when an elective is selected by user.
     * Recalculates moduleWeights with a slight bias towards chosen elective.
     */
    public static void recalculateWeights() {

        // take moduleWeight
        // iterate across weights in moduleWeight
            // recalculate new weight and replace
            // repeat
    }
}