package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

/**
 * Creates a record for a new user
 * might need marks??? modules????
 */
public class RecordCreator extends DatabaseHandler{


    private int studentId;

    private String studentName;

    private String courseName;

    private int studentYear;

    private String studentPassword;

    /**
     *
     * @param studentId id of the new student
     * @param studentName first and last name of the student
     * @param courseName name of the course taken by the student
     * @param studentYear which year the student is
     * @param studentPassword student's account password
     */
    public RecordCreator(int studentId, String studentName, String courseName, int studentYear, String studentPassword) {

        this.studentId = studentId;

        this.studentName = studentName;

        this.courseName = courseName;

        this.studentYear = studentYear;

        this.studentPassword = studentPassword;
    }

    @Override
    public void save() {;}
}
