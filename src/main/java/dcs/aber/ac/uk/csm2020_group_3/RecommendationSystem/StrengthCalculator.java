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


        // sort modules
        Collections.sort(modules);

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
}
