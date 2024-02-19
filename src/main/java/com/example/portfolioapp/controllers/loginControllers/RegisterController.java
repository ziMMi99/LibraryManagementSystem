package com.example.portfolioapp.controllers.loginControllers;

import com.example.portfolioapp.data.DataLayer;
import com.example.portfolioapp.dbo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class RegisterController {

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField emailField;
    @FXML
    TextField firstnameField;
    @FXML
    TextField lastnameField;
    @FXML
    Hyperlink loginHyperlink;


    public void setLoginHyperlinkAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/portfolioapp/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/portfolioapp/login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setRegisterButtonAction(ActionEvent event) throws NoSuchAlgorithmException {
        if (usernameField.getText().isBlank() ||
                passwordField.getText().isBlank() ||
                emailField.getText().isBlank() ||
                firstnameField.getText().isBlank() ||
                lastnameField.getText().isBlank()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hey there!");
            alert.setContentText("Please fill out everything!");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isEmpty()) {
                System.out.println("Alert closed");
            }
            return; // At least one field is empty, so return without processing
        }

        // All fields are filled, proceed with processing
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();

        //Hasing password using SHA-256 algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(password.getBytes());

        StringBuilder hashedPassword = new StringBuilder();
        for (byte b : hash) {
            hashedPassword.append(String.format("%02x", b));
        }
        //Create user with aquired information
        User user = new User(username, hashedPassword.toString(), email, firstname, lastname);

        try {
            DataLayer data = new DataLayer();
            data.userRegistration(user);
            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hey there " + username);
        alert.setContentText("Your new account was created");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty()) {
            System.out.println("Alert closed");
        } else if (result.get() == ButtonType.OK) {
            try {
                switchToLogin(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
