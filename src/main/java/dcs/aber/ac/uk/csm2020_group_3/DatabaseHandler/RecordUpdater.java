package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;

/**
 * Updates any specified record with new information
 */
public class RecordUpdater extends DatabaseHandler{

    private final Table typeOfRecord;

    private final Record recordToUpdate;

    private final Record newRecordDetails;

    /**
     * Constructs an Object that given an instance of Record's subclass, can update this record with new details
     * @param recordObject record object to update
     * @param newRecordDetails record object containing new details
     */
    public RecordUpdater(Record recordObject, Record newRecordDetails) {
        this.typeOfRecord = recordObject.getTableName();

        this.recordToUpdate = recordObject;

        this.newRecordDetails = newRecordDetails;
    }


}
