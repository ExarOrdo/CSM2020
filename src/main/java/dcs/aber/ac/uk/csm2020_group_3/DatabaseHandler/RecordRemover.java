package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

/**
 * Removes student records from the db
 */
public class RecordRemover extends DatabaseHandler{

    private String studentId;

    private String studentPassword;

    /**
     *
     * @param studentId id of the student being removed
     * @param studentPassword student's password????
     */
    public RecordRemover(String studentId, String studentPassword) {
        this.studentId = studentId;

        this.studentPassword = studentPassword;

    }

    @Override
    public void load() {;}

    @Override
    public void remove() {;}
}
