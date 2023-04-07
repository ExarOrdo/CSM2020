package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

public class ModuleInfo {
    private String moduleName;
    private int moduleCredits;
    private int moduleSemester;
    private String tag1;
    private String tag2;
    private String tag3;

    public ModuleInfo(String moduleName, int moduleCredits, int moduleSemester, String tag1, String tag2, String tag3) {
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getModuleCredits() {
        return moduleCredits;
    }

    public int getModuleSemester() {
        return moduleSemester;
    }
}
