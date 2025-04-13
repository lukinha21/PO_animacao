module com.example.poanimacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.poanimacao to javafx.fxml;
    exports com.example.poanimacao;
}