package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.ModuleHandler;

/**
 * Class responsible for generating and calculating similarity between modules (weights)
 * Gets tags from ResultSet objects from the DataLoader.
 */
public class WeightGenerator extends ModuleHandler {


    /**
     * Bias determines how much current electives change the weights of suggested electives, from 1-4 (for now)
     */
    private Integer bias = 2;
    private ElectiveListGenerator electiveListGenerator;

    private CoreListGenerator coreListGenerator;

    public WeightGenerator(CoreListGenerator coreListGenerator, ElectiveListGenerator electiveListGenerator) {
        this.coreListGenerator = coreListGenerator;
        this.electiveListGenerator = electiveListGenerator;
    }

    /**
     * Method that gets tags from Module Objects - might not be needed.
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
    public void generateWeights(ArrayList<Module> currentCores, ArrayList<Module> currentElectives) {

        // run for loop with jaccards
        for (Module currentElective : currentElectives) {
            float moduleWeight = 0;
            for (Module currentCore : currentCores) {
                moduleWeight += calculateWeight(currentElective.getTagList(), currentCore.getTagList());

            }
            // normalize moduleWeight, put in dict with module name, set public variable to temp variable.
            currentElective.setWeight(moduleWeight / currentCores.size());
            System.out.println(currentElective.getName());
            System.out.println(currentElective.getWeight());
        }
    }

    /**
     * Method that is called when an elective is selected by user.
     * Recalculates moduleWeights with a slight bias towards chosen elective.
     * ****************************
     * THIS FUNCTION ASSUMES THAT UPON SELECTING a MODULE IT IS ADDED TO A LIST, WHICH IS USED IN CALCULATION BELOW
     */
    public void recalculateWeights() {

        Float newWeight = 0f;

        // **********HALP PLS*********
        //I want to access the variables:
        getModuleAmount();
        currentModuleAmount;

        // have updated electivelist with chosen removed
        // iterate through modules in electiveList
        // modify their weights by:

        // weight = old weight * (old amount/new amount) + bias * ( newWeight / electiveList.size() )
        // where old amount = all modules - newly added.
        // where new amount = newlyaddedmodules.size()
        // where newWeight = calculateWeight( newly added module/modules, electiveList with new ones removed)


        // iterate across modules in electives list
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {

            // assign modules new weights
            ElectiveListGenerator.electiveModulesList.get(i).setWeight(                    );


            ElectiveListGenerator.electiveModulesList.get(i).getWeight() * (       /newlyAddedModules.size())
        }

    }
}