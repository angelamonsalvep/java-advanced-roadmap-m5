module com.riwi.test_view {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.riwi.test_view to javafx.fxml;
    exports com.riwi.test_view;
}