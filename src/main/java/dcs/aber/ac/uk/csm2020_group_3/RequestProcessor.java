package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.*;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.Recommender;

/**
 * Class responsible for handling any requests made by the UI.
 * It forwards these to appropriate systems and deals with their
 * inputs and outputs.
 */
public class RequestProcessor {

    private Login loginObject;

    private Register registerObject;

    private RecordCreator recordCreator;

    private RecordRemover recordRemover;

    private RecordUpdater recordUpdater;

    private RecordLoader recordLoader;

    private Recommender recommender;
}
