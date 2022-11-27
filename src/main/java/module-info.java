module com.irgashevsamir.javafx_samples {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.irgashevsamir.javafx_samples to javafx.fxml;
    exports com.irgashevsamir.javafx_samples;
}