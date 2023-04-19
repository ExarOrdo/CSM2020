package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import javafx.scene.control.ComboBox;

import java.sql.*;

/**
 * Creates new account with given data,
 * depends on what RecordCreator is meant to be
 */
public class Register extends DatabaseHandler{

    private final String studentId;
    private final String password;
    private final String firstName;
    private final String lastName;
    private String year ="";
    private String course= "";
    private final ComboBox initalCourse;
    private final ComboBox initalYear;


    public Register(String studentId, String password, String firstName, String lastName, ComboBox year, ComboBox course){
        this.studentId = studentId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initalYear = year;
        this.initalCourse = course;
        currentStudentId = studentId;
    }

    public boolean studentExists() throws SQLException{
        this.connection = DriverManager.getConnection(connectionString);
        try {
            Statement statement = connection.createStatement();
            String query = ("SELECT * FROM STUDENT WHERE StudentID = '" + studentId + "'");
            ResultSet studentExists = statement.executeQuery(query);

            if (studentExists.next()) {
                System.out.println("Student ID already exists.");
                return false;
            }
            else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean allFieldsFilled() {
        if (firstName.isEmpty() || lastName.isEmpty() || studentId.isEmpty() || password.isEmpty() || initalYear.getValue() == null || initalCourse.getValue() == null){
            return false;

        }
        else{
            year = initalYear.getValue().toString();
            course = initalCourse.getValue().toString();

            return true;
        }

    }

    public void tryRegister() throws SQLException {
        //this.connection = DriverManager.getConnection(connectionString);
        this.connection = getConnection();
        try{
                String fullName = firstName + lastName;
                PreparedStatement createStudent = connection.prepareStatement("INSERT INTO STUDENT (StudentID,StudentName,StudentCourse,StudentYear,StudentPassword) VALUES (?,?,?,?,?)");
                createStudent.setString(1,this.studentId);
                createStudent.setString(2,fullName);
                createStudent.setString(3,this.course);
                createStudent.setInt(4, Integer.parseInt(this.year));
                createStudent.setString(5,this.password);
                createStudent.execute();
                createStudent.close();
                System.out.println(currentStudentId);


        }catch  (Exception err) {
            System.err.println("Error:" + err.getMessage());
            err.printStackTrace();
        }


    }


}
