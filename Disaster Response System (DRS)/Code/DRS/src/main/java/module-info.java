module cqu.drs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Required for JDBC operations

    opens cqu.drs to javafx.fxml;
    exports cqu.drs;
}
