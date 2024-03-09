module com.example.appointmentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.appointmentmanager to javafx.fxml;
    exports com.example.appointmentmanager;
}