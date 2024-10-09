module cqu.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires transitive java.mail;
    requires transitive java.sql;
    

    opens cqu.mavenproject1 to javafx.fxml;
    exports cqu.mavenproject1;
}
