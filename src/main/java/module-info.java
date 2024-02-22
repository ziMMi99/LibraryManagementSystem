module com.example.PortfolioApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.portfolioapp to javafx.fxml;
    exports com.example.portfolioapp;
    exports com.example.portfolioapp.controllers.loginControllers;
    opens com.example.portfolioapp.controllers.loginControllers to javafx.fxml;
    exports com.example.portfolioapp.controllers.libraryController;
    opens com.example.portfolioapp.controllers.libraryController to javafx.fxml;
}