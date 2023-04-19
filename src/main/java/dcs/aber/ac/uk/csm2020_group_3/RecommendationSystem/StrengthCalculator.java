package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Strength will only be needed if we have multiple options the user can fiddle with,
 * other than the simple numerical value of weight to represent similarity can be used
 * The former is preferred.
 */

public class StrengthCalculator extends ModuleHandler{


    public static ArrayList<Module> highStrength;
    public static ArrayList<Module> mediumStrength;
    public static ArrayList<Module> lowStrength;

    /**
     * Sort Weights into 3 separate lists for UI
     * @param modules
     */

    public void sortByWeights(ArrayList<Module> modules) {
        highStrength = new ArrayList<Module>();
        mediumStrength = new ArrayList<Module>();
        lowStrength = new ArrayList<Module>();

        //print modules by weight before and after
        System.out.println("BEFORE SOR");
        for (int i = 0; i < modules.size(); i++){
            System.out.println(modules.get(i).getName());
            System.out.println(modules.get(i).getWeight());
        }

        // sort modules
        Collections.sort(modules);
        Collections.reverse(modules);


        System.out.println("AFTER SORT");
        for (int i = 0; i < modules.size(); i++){
            System.out.println(modules.get(i).getName());
            System.out.println(modules.get(i).getWeight());
        }

        // split into three lists
        for ( int i = 0; i < (int) Math.ceil(modules.size() / 3); i++ ){
            highStrength.add(modules.get(i));
        }

        for ( int j = (int) Math.ceil(modules.size() / 3); j < (int) Math.ceil((modules.size() / 3*2)); j++ ){
            mediumStrength.add(modules.get(j));

        }

        for ( int k = (int) Math.ceil((modules.size() / 3*2)); k < modules.size(); k++ ){
            lowStrength.add(modules.get(k));
        }


        System.out.println(highStrength);
        System.out.println(mediumStrength);
        System.out.println(lowStrength);


    }

    /**
     * Function that recalculates a module's weight by adding bias to certain tags in modules
     * Iterates over electiveList, check every chosenModules for a tag, times by scalar.
     * How hard to check every module for tag?
     */
    public void prioritizeTag(String tag){

        // iterate over electives and chosenModules
        // for each elective, check if existence of bool in Module.
        // define prioritize, switches to check for each tag
        // if "call dict key" = True.
        //      set module.weight = (oldweight + bias* tag weight) / amount
        // Sum of all weights for a module.

        //for
        //      for
        //          check for tag, set weight depending on tags.
        //      take sums of all weights and set equal to global
        //
        // instantly check this whilst iterating over all electives + chosen.
        //

    }
}
