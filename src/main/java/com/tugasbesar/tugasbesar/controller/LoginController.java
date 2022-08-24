package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.HelloApplication;
import com.tugasbesar.tugasbesar.dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    public PasswordField password;
    public TextField username;
    public Pane root;

    public void loginAccount(ActionEvent actionEvent) {

        UserDao userDao = new UserDao();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getText().getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            String generatedPassword = sb.toString();
            System.out.println(generatedPassword);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public void newAccount(ActionEvent actionEvent) throws IOException {

        Parent newRoot = FXMLLoader.load(HelloApplication.class.getResource("signup-view.fxml"));
        root.getScene().setRoot(newRoot);

    }
}
