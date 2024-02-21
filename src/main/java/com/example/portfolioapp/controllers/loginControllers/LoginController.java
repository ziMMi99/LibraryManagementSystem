package com.example.portfolioapp.controllers.loginControllers;

import com.example.portfolioapp.data.DataLayer;
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

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void setSignupHyperlinkAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/portfolioapp/register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/portfolioapp/libraryUser.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        DataLayer data = new DataLayer();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());

        StringBuilder HashedInputPassword = new StringBuilder();
        for (byte b : hash) {
            HashedInputPassword.append(String.format("%02x", b));
        }

        return data.getHashedPassword(username).contentEquals(HashedInputPassword);
    }


    public void setLoginButtonAction(ActionEvent event)  {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        try {
            boolean matching = checkUsernameAndPassword(inputUsername, inputPassword);

            if (matching) {
                switchToHome(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setContentText("Username and password does not match!");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isEmpty()) {
                    System.out.println("Alert closed");
                }
            }
        } catch (IOException e) {
            System.out.println("Could not switch to main page - " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not find algorithm - " + e.getMessage());
        }

    }
}