package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class RecordLoader extends DatabaseHandler{

    public int getStudentYear(String studentId) throws SQLException {

        this.connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT StudentYear FROM STUDENT WHERE StudentID = ?");
            statement.setString(1, studentId);

            ResultSet studentYear = statement.executeQuery();

            studentYear.next();
            return studentYear.getInt("StudentYear");

        } catch (Exception err) {
            System.err.println("Error during getting student year: " + err.getMessage());
            err.printStackTrace();
        }
        return -1;
    }

    public ArrayList<String> getSubjectList() throws SQLException {
        ArrayList<String> subjectList = new ArrayList<>();

        this.connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT ModuleTag1 FROM MODULE");
            ResultSet subjectResultSet = statement.executeQuery();

            while (subjectResultSet.next()) {
                subjectList.add(subjectResultSet.getString("ModuleTag1"));
                System.out.println(subjectResultSet.getString("ModuleTag1"));
            }


        } catch (Exception err) {
            System.err.println("Error during getting subject list for admin page: " + err.getMessage());
            err.printStackTrace();
        }

        Collections.sort(subjectList);

        return subjectList;
    }

    public ArrayList<String> getCourseListBySubject(String subject) throws SQLException {
        ArrayList<String> courseList = new ArrayList<>();

        this.connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT CourseName FROM COURSE JOIN CORE_MODULE ON CORE_MODULE.CourseID = COURSE.CourseID " +
                    "JOIN MODULE ON MODULE.ModuleID = CORE_MODULE.ModuleID WHERE MODULE.ModuleTag1 = ?");
            statement.setString(1, subject);
            ResultSet courseResultSet = statement.executeQuery();

            while (courseResultSet.next()) {

                courseList.add(courseResultSet.getString("CourseName"));
                System.out.println(courseResultSet.getString("CourseName"));
            }
        } catch (Exception err) {
            System.err.println("Error during getting course list for admin page: " + err.getMessage());
            err.printStackTrace();
        }

        Collections.sort(courseList);
        return courseList;
    }

    public ArrayList<String> getModuleListByCourse(String course, int year) throws SQLException {
        ArrayList<String> moduleStringList = new ArrayList<>();

        this.connection = getConnection();

        try {
            PreparedStatement coreStatement = connection.prepareStatement("SELECT MODULE.ModuleName FROM MODULE JOIN CORE_MODULE ON CORE_MODULE.ModuleID = MODULE.ModuleID" +
                    " JOIN COURSE ON COURSE.CourseID = CORE_MODULE.CourseID WHERE COURSE.CourseName = ? AND MODULE.ModuleYear = ?");
            coreStatement.setString(1, course);
            coreStatement.setInt(2, year);

            PreparedStatement optionalStatement = connection.prepareStatement("SELECT MODULE.ModuleName FROM MODULE JOIN OPTIONAL_MODULE ON OPTIONAL_MODULE.ModuleID = MODULE.ModuleID " +
                    "JOIN COURSE ON COURSE.CourseID = OPTIONAL_MODULE.CourseID WHERE COURSE.CourseName = ? AND MODULE.ModuleYear = ?");
            optionalStatement.setString(1, course);
            optionalStatement.setInt(2, year);

            ResultSet coreResultSet = coreStatement.executeQuery();
            ResultSet optionalResultSet = optionalStatement.executeQuery();

            //rewrite cores
            while (coreResultSet.next()) {
                moduleStringList.add(coreResultSet.getString("ModuleName"));
                System.out.println(coreResultSet.getString("ModuleName"));
            }

            //rewrite optionals
            while (optionalResultSet.next()) {
                moduleStringList.add(optionalResultSet.getString("ModuleName"));
                System.out.println(optionalResultSet.getString("ModuleName"));
            }


        } catch (Exception err) {
            System.err.println(err.getMessage());
            err.printStackTrace();
        }

        Collections.sort(moduleStringList);
        return moduleStringList;
    }
}
