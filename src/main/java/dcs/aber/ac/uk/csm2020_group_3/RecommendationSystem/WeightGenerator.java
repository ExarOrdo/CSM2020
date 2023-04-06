package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;

/**
 * Class responsible for generating and calculating similarity between modules (weights)
 * Gets tags from ResultSet objects from the DataLoader.
 */
public class WeightGenerator {

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
     * Gets currently enrolled modules for student.
     * Gets currently selected modules for student.
     * Takes optional module as an argument.
     * Calculates the weights between optional module and enrolled + selected modules.
     * Returns an arrayList of weights
     */
    public static void GetWeights() {

        // use studentID
        // get the required module data
        // access currentModuleWeights
        // add to it
        ;
    }

}