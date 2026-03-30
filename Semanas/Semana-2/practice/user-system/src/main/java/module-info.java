module com.riwi.usersystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.riwi.usersystem to javafx.fxml;
    exports com.riwi.usersystem;
}