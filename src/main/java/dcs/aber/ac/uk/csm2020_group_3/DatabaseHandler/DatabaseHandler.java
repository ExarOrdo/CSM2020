package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

/**
 * An abstract class that will be inherited by various
 * classes that interact with the database, but
 * will need to perform different actions based
 * on their goal.
 */
abstract class DatabaseHandler {

    /**
     * method for loading specific information from the db
     */
    public void load(){;}

    /**
     * Method for saving specific information to the db
     */
    public void save() {;}

    /**
     * Method for removing specified data from the db
     */
    public void remove() {;}

    /**
     * Method for adding data into specific place in the db
     */
    public void add() {;}

    /**
     * Method for editing already existing data in the db
     */
    public void update() {;}


}
