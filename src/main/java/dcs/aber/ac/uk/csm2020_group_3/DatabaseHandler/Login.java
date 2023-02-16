package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

/**
 * Class used for logging in, checks login credentials in the db
 */
public class Login extends DatabaseHandler {

    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void load() {;}

}
