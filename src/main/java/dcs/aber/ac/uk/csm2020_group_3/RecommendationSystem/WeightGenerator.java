package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class responsible for generating and calculating similarity between modules (weights)
 * Gets tags from ResultSet objects from the DataLoader.
 */
public class WeightGenerator extends ModuleHandler {


    /**
     * Bias determines how much current electives change the weights of suggested electives, from 1-4 (for now)
     */
    private final Integer bias = 1;
    private final ElectiveListGenerator electiveListGenerator;

    private final CoreListGenerator coreListGenerator;

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
     * Changes Module weights of modules in electivesList
     */
    public void recalculateWeightsOnAdd() {


        // takes electiveList
        // takes modulesToBeMoved list
        // get from modulesToBeMoved
        // calculate jaccrds from electivesList to modulesToBeMoved list
        // set weight to elective

        // weight = old weight * (old amount/new amount) + bias * ( newWeight / electiveList.size() )
        // where old amount = all modules - newly added.
        // where new amount = newlyaddedmodules.size()
        // where newWeight = calculateWeight( newly added module/modules, electiveList with new ones removed)


        // iterate across modules in electives list
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {

            float moduleWeight = 0;

            // assign modules new weights
            //System.out.println(getModuleAmount());
            //System.out.println(modulesToBeMoved);

            //

            // recalculate each elective weight w.r.t newlyAddedElectives
            for (int j = 0; j < modulesToBeMoved.size(); j++){

                // set new weight, where:
                System.out.println("BEFORECALC");
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getName());
                System.out.println(modulesToBeMoved.get(j).getName());
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getName());
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight());
                System.out.println(((float) (ElectiveListGenerator.electiveModulesList.size() - modulesToBeMoved.size())  / ElectiveListGenerator.electiveModulesList.size()) );
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() - modulesToBeMoved.size())  / ElectiveListGenerator.electiveModulesList.size())  );
                System.out.println(bias * ( calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList())/ ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() - modulesToBeMoved.size())/ ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() - modulesToBeMoved.size())/ ElectiveListGenerator.electiveModulesList.size()) + (bias * ( calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList())/ ElectiveListGenerator.electiveModulesList.size())));

                // weight = old weight * (old amount/new amount) + bias * ( newWeight / electiveList.size() )
                ElectiveListGenerator.electiveModulesList.get(i).setWeight(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() - modulesToBeMoved.size())/ ElectiveListGenerator.electiveModulesList.size()) + bias * ( calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList())/ ElectiveListGenerator.electiveModulesList.size()));

            //calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), newlyAddedModules.get(j).getTagList())
            //ElectiveListGenerator.electiveModulesList.get(i).getWeight() * (getModuleAmount()/newlyAddedModules.size()) + 2 * ( calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), newlyAddedModules.get(j).getTagList())/ newlyAddedModules.size())
            // weights are set, but then moduleweight needs to be set.


            System.out.println("Chosen Elective :");
            System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getName());
            System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight());
            }
        }

    }

    public void recalculateWeightsOnRemove() {

        //newElectiveList = ElectiveListGenerator.electiveModulesList.remove(modulesToBeMoved);
        // take module we are adding back in
        // for each old electiveList we calculate jaccrds w/ modulesMoved
        // take from old electiveList weights, add modulesMoved in.

        // weight = old weight * (old amount/new amount) + bias * ( newWeight / electiveList.size() )

        System.out.println("MOVEMODULE");
        System.out.println(modulesToBeMoved.get(0).getName());
        System.out.println(modulesToBeMoved.get(0).getWeight());


        // iterate across modules in electives list
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {

            // assign modules new weights
            //System.out.println(getModuleAmount());
            //System.out.println(modulesToBeMoved);

            // recalculate each elective weight w.r.t newlyAddedElectives
            for (int j = 0; j < modulesToBeMoved.size(); j++) {

                // set new weight, where:
                System.out.println("REMOVECALC");
                System.out.println("chosen elective");
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight());
                System.out.println(((float) (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()) / ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()) / ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(bias * (calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()) / ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()) / ElectiveListGenerator.electiveModulesList.size()));
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()) / ElectiveListGenerator.electiveModulesList.size()) - (bias * (calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()) / (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()))));
                System.out.println(calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()) / (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()));
                System.out.println("similarity");
                System.out.println(calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()));
                System.out.println(calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()) / (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()));

                // weight = old weight * (old amount/new amount) + bias * ( newWeight / electiveList.size() )
                ElectiveListGenerator.electiveModulesList.get(i).setWeight(ElectiveListGenerator.electiveModulesList.get(i).getWeight() * ((float) (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size()) / ElectiveListGenerator.electiveModulesList.size()) - bias * (calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), modulesToBeMoved.get(j).getTagList()) / (ElectiveListGenerator.electiveModulesList.size() + modulesToBeMoved.size())));

                //calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), newlyAddedModules.get(j).getTagList())
                //ElectiveListGenerator.electiveModulesList.get(i).getWeight() * (getModuleAmount()/newlyAddedModules.size()) + 2 * ( calculateWeight(ElectiveListGenerator.electiveModulesList.get(i).getTagList(), newlyAddedModules.get(j).getTagList())/ newlyAddedModules.size())
                // weights are set, but then moduleweight needs to be set.


                System.out.println("Chosen Elective :");
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getName());
                System.out.println(ElectiveListGenerator.electiveModulesList.get(i).getWeight());
            }


        }
        System.out.println("MOVEMODULE");
        System.out.println(modulesToBeMoved.get(0).getName());
        System.out.println(modulesToBeMoved.get(0).getWeight());

        ElectiveListGenerator.electiveModulesList.addAll(modulesToBeMoved);

        for (int z = 0; z < ElectiveListGenerator.electiveModulesList.size(); z++){
            System.out.println(ElectiveListGenerator.electiveModulesList.get(z).getName());
            System.out.println(ElectiveListGenerator.electiveModulesList.get(z).getWeight());
        }

    }
}
