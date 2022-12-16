module com.example.surplusapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.surplusapp to javafx.fxml;
    exports com.example.surplusapp;
}