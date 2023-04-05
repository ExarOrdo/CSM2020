package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

public class ModuleInfo {
    private String moduleName;
    private int moduleCredits;
    private int moduleSemester;

    public ModuleInfo(String moduleName, int moduleCredits, int moduleSemester) {
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
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
