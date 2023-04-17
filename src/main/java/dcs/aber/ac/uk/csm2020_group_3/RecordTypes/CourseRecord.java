package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

public class CourseRecord extends Record{

    private String courseId;

    private String courseName;

    private String courseDescription;

    /**
     * Construct an object holding data about a single Course record
     * @param courseId
     * @param courseName
     * @param courseDescription
     */
    public CourseRecord(String courseId, String courseName, String courseDescription) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;

        this.setNumberOfFields(3);
        this.setTableName(Table.COURSE);
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
