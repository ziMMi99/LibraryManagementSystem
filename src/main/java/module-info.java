module com.example.portfolioapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.portfolioapp to javafx.fxml;
    exports com.example.portfolioapp;
    exports com.example.portfolioapp.controllers.homeControllers;
    opens com.example.portfolioapp.controllers.homeControllers to javafx.fxml;
    exports com.example.portfolioapp.controllers.loginControllers;
    opens com.example.portfolioapp.controllers.loginControllers to javafx.fxml;
}