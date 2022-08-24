package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.HelloApplication;
import com.tugasbesar.tugasbesar.dao.UserDao;
import com.tugasbesar.tugasbesar.model.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignupController {
    public PasswordField password;
    public TextField username;
    public PasswordField passwordConfirm;
    public Pane root;

    public String hash(){

        String generatedPassword;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getText().getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            System.out.println(generatedPassword);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return generatedPassword;

    }

    public void signUpAccount(ActionEvent actionEvent) {

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        if (Objects.equals(password.getText(), passwordConfirm.getText())) {
            String newPass = hash();
            userEntity.setUsername(username.getText());
            userEntity.setPassword(newPass);
            int hasil = userDao.addData(userEntity);

            if (hasil > 0){
                toMain();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Confirmation Password does not match");
            alert.showAndWait();

        }

    }

    public void loginAccount(ActionEvent actionEvent) throws IOException {
        Parent newRoot = FXMLLoader.load(HelloApplication.class.getResource("login-view.fxml"));
        root.getScene().setRoot(newRoot);
    }

    public void toMain(){

        Parent newRoot = null;
        try {
            newRoot = FXMLLoader.load(HelloApplication.class.getResource("main-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getScene().setRoot(newRoot);

    }

}
