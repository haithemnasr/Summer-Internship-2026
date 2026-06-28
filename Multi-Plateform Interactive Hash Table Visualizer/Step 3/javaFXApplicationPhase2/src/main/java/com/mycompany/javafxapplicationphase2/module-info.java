module com.mycompany.javafxapplicationphase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens com.mycompany.javafxapplicationphase2 to javafx.fxml;
    exports com.mycompany.javafxapplicationphase2;
}