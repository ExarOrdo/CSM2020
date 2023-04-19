package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

/**
 * Class for holding information about optional module record, used when dealing with db
 */
public class OptionalModuleRecord extends Record {

    private String courseId;

    private String moduleId;


    /**
     * Construct a single optional module record for Optional Module table
     * @param courseId course id
     * @param moduleId module id
     */
    public OptionalModuleRecord(String courseId, String moduleId) {
        this.courseId = courseId;

        this.moduleId = moduleId;


        //change the number to how many private variables the class has, or how many field the table has
        this.setNumberOfFields(2);
        this.setTableName(Table.OPTIONAL_MODULE);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getModuleId() {
        return moduleId;
    }
}
