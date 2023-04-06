package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;

/**
 * Creates a record for a new user
 * might need marks??? modules????
 */
public class RecordCreator extends DatabaseHandler{

    private Table typeOfRecord;
    private Record recordObject;

    public RecordCreator(Record recordObject) {
        this.recordObject = recordObject;

        this.typeOfRecord = recordObject.getTableName();
    }

    public boolean tryCreatingRecord() {




        //apparently switch Pattern Matching is not supported in java 18 so enjoy extra variable band-aid
        switch (typeOfRecord) {
            case STUDENT:

                break;
            case OPTIONAL_MODULE:

                break;
            case CORE_MODULE:

                break;
            case MODULE:

                break;
            case MARKS:

                break;
            case COURSE:

                break;
            default:
                System.err.println("Unknown type of Record subclass instance type.");
                break;
        }



        return true;
    }
}
