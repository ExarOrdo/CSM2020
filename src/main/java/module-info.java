module dcs.aber.ac.uk.csm2020_group_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler to javafx.base;
    opens dcs.aber.ac.uk.csm2020_group_3 to javafx.fxml;
    exports dcs.aber.ac.uk.csm2020_group_3;
    exports dcs.aber.ac.uk.csm2020_group_3.UI;
    opens dcs.aber.ac.uk.csm2020_group_3.UI to javafx.fxml;
}