package dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem;

import java.util.ArrayList;

public class Module implements Comparable<Module> {
    private float Weight;
    private String Name;
    private int Credits;
    private int Semester;
    private int Year;
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;
    private String tag6;
    private String tag7;
    private String tag8;

    public Module(String Name, int Credits, int Semester, int Year, String tag1) {
        this.Name = Name;
        this.Credits = Credits;
        this.Semester = Semester;
        this.Year = Year;
        this.tag1 = tag1;
        this.tag2 = null;
        this.tag3 = null;
        this.tag4 = null;
        this.tag5 = null;
        this.tag6 = null;
        this.tag7 = null;
        this.tag8 = null;
        this.Weight = 0f;

    }

    public void setModuleTag(String tagString, Integer tagIndex) {
        if (tagIndex == 2) {
            tag2 = tagString;
        } else if (tagIndex == 3) {
            tag3 = tagString;
        } else if (tagIndex == 4) {
            tag4 = tagString;
        } else if (tagIndex == 5) {
            tag5 = tagString;
        } else if (tagIndex == 6) {
            tag6 = tagString;
        } else if (tagIndex == 7) {
            tag7 = tagString;
        } else if (tagIndex == 8) {
            tag8 = tagString;
        }
    }


    public String getName() {
        return Name;
    }

    public int getCredits() {
        return Credits;
    }

    public int getSemester() {
        return Semester;
    }

    public int getYear() {
        return this.Year;
    }

    public float getWeight() {
        return this.Weight;
    }

    public void setWeight(Float weight) {
        this.Weight = weight;
    }

    public ArrayList<String> getTagList() {
        ArrayList<String> tagList = new ArrayList<>();
        if (tag1 != null) {
            tagList.add(tag1);
        }
        if (tag2 != null) {
            tagList.add(tag2);
        }
        if (tag3 != null) {
            tagList.add(tag3);
        }
        if (tag4 != null) {
            tagList.add(tag4);
        }
        if (tag5 != null) {
            tagList.add(tag5);
        }
        if (tag6 != null) {
            tagList.add(tag6);
        }
        if (tag7 != null) {
            tagList.add(tag7);
        }
        if (tag8 != null) {
            tagList.add(tag8);
        }

        return tagList;
    }

    @Override
    public int compareTo(Module otherModule) {
        return Float.compare(this.Weight, otherModule.Weight);
    }

}
