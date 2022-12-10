module com.irgashevsamir.javafx_samples {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires javafx.graphics;

    opens com.irgashevsamir.javafx_samples to javafx.fxml;
    exports com.irgashevsamir.javafx_samples;
}