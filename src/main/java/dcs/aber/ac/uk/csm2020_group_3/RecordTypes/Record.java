package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

public class Record {

    protected int numberOfFields;

    protected Table tableName;




    public Table getTableName() {
        return this.tableName;
    }

    public int getNumberOfFields() {
        return this.numberOfFields;
    }




    public void setNumberOfFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }

    public void setTableName(Table tableName) {
        this.tableName = tableName;
    }
}
