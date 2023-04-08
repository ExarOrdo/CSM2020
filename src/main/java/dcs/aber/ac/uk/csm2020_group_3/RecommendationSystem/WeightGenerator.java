package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class responsible for generating and calculating similarity between modules (weights)
 * Gets tags from ResultSet objects from the DataLoader.
 */
public class WeightGenerator {

    public static HashMap<String, Float> electivesWeightDict;

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
    public void generateWeights(ArrayList<ModuleInfo> currentCores, ArrayList<ModuleInfo> currentElectives) {

        // initialize counter variables
        electivesWeightDict = new HashMap<>();

        // run for loop with jaccards
        for (ModuleInfo currentElective : currentElectives) {
            float moduleWeight = 0;
            for (ModuleInfo currentCore : currentCores) {
                moduleWeight += calculateWeight(currentElective.getTagList(), currentCore.getTagList());

            }
            // normalize moduleWeight, put in dict with module name, set public variable to temp variable.
            electivesWeightDict.put(currentElective.getModuleName(), moduleWeight / currentCores.size());
        }
        System.out.println(electivesWeightDict);
        }

    /**
     * Method that is called when an elective is selected by user.
     * Recalculates moduleWeights with a slight bias towards chosen elective.
     * ****************************
     * THIS FUNCTION ASSUMES THAT UPON SELECTING a MODULE IT IS ADDED TO A LIST, WHICH IS USED IN CALCULATION BELOW
     */
    public static void recalculateWeights() {

        Float newWeight = 0f;

        // take list of electivesWeightDict values and keys
        ArrayList<Float> electivesWeightList = new ArrayList<Float>(electivesWeightDict.values());
        ArrayList<String> electivesWeightKeyList = new ArrayList<String>(electivesWeightDict.keySet());

        // iterate across weights
        for (int i = 0; i < electivesWeightList.size(); i++){
            ;
            // put in recalculated value into Dict
            //electivesWeightDict.put( (electivesWeightKeyList.get(i)), (electivesWeightDict.get(electivesWeightKeyList.get(i)))* REST OF CALCULATION THAT NEEDS
            // SELECTED MODULES BEFORE ELECTIVE IS CHOSEN + SELECTED MODULES AFTER ELECTIVE IS CHOSEN BY CONTROLLER);
        }
            // recalculate new weight and replace
            // repeat
    }
}