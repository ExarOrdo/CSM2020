package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

public class ModuleRecord extends Record {

    private String moduleId;

    private String moduleName;

    private String moduleDescription;

    private int moduleCredits;

    private int moduleYear;

    private int moduleSemester;

    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;
    private String tag6;
    private String tag7;
    private String tag8;

    private String modulePrerequisite;


    /**
     * Constructs module record object
     * @param moduleId
     * @param moduleName
     * @param moduleDescription
     * @param moduleCredits
     * @param moduleYear
     * @param moduleSemester
     * @param tag1
     * @param tag2
     * @param tag3
     */
    public ModuleRecord(String moduleId, String moduleName, String moduleDescription,
                        int moduleCredits, int moduleYear, int moduleSemester,
                        String tag1, String tag2, String tag3, String tag4, String tag5,
                        String tag6, String tag7, String tag8, String modulePrerequisite) {

        this.moduleId = moduleId;

        this.moduleName = moduleName;

        this.moduleDescription = moduleDescription;

        this.moduleCredits = moduleCredits;

        this.moduleYear = moduleYear;

        this. moduleSemester = moduleSemester;

        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.tag5 = tag5;
        this.tag6 = tag6;
        this.tag7 = tag7;
        this.tag8 = tag8;

        this.modulePrerequisite = modulePrerequisite;

        this.setNumberOfFields(15);
        this.setTableName(Table.MODULE);
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public int getModuleCredits() {
        return moduleCredits;
    }

    public int getModuleYear() {
        return moduleYear;
    }

    public int getModuleSemester() {
        return moduleSemester;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public String getTag5() {
        return tag5;
    }

    public String getTag6() {
        return tag6;
    }

    public String getTag7() {
        return tag7;
    }

    public String getTag8() {
        return tag8;
    }

    public String getModulePrerequisite() {
        return modulePrerequisite;
    }
}
