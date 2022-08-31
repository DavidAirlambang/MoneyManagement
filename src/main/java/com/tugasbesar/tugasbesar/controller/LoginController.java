package com.tugasbesar.tugasbesar.controller;

import com.google.gson.Gson;
import com.tugasbesar.tugasbesar.HelloApplication;
import com.tugasbesar.tugasbesar.dao.UserDao;
import com.tugasbesar.tugasbesar.model.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    public PasswordField password;
    public TextField colUsername;
    public Pane root;
    private ObservableList<UserEntity> users;

    public void loginAccount(ActionEvent actionEvent) {

        UserDao userDao = new UserDao();

        if (password.getText().isEmpty() || colUsername.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Isi semua field");
            alert.showAndWait();
        } else {
            try {
                // Buat hash
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getText().getBytes());
                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                String generatedPassword = sb.toString();

                // Validasi di Dao
                int hasil = userDao.Validator(colUsername.getText(), generatedPassword);

                if (hasil != 0) {
                    loggedIn();
                    toMain();
                }

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void newAccount(ActionEvent actionEvent) throws IOException {

        Parent newRoot = FXMLLoader.load(HelloApplication.class.getResource("signup-view.fxml"));
        root.getScene().setRoot(newRoot);

    }

    public void toMain(){

        Parent newRoot = null;
        try {
            newRoot = FXMLLoader.load(HelloApplication.class.getResource("menu-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getScene().setRoot(newRoot);

    }

    public void loggedIn() {

        BufferedWriter writer;
        String filename = "data/logged.txt";
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(colUsername.getText());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
