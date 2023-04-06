package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

public class CoreModuleRecord extends Record{

    private String courseId;

    private String moduleId;

    /**
     * Constructs an object that holds data about single Core Module record
     * @param courseId
     * @param moduleId
     */
    public CoreModuleRecord(String courseId, String moduleId) {
        this.courseId = courseId;
        this.moduleId = moduleId;

        this.setNumberOfFields(2);
        this.setTableName(Table.CORE_MODULE);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getModuleId() {
        return moduleId;
    }
}
