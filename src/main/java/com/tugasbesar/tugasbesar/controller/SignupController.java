package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {
    public PasswordField password;
    public TextField username;
    public PasswordField passwordConfirm;
    public Pane root;


    public void signUpAccount(ActionEvent actionEvent) {


    }

    public void loginAccount(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(HelloApplication.class.getResource("login-view.fxml"));
        root.getScene().setRoot(newRoot);
    }

}
