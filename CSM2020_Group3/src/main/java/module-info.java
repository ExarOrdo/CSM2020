module dcs.aber.ac.uk.csm2020_group3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens dcs.aber.ac.uk.csm2020_group3 to javafx.fxml;
    exports dcs.aber.ac.uk.csm2020_group3;
}