package com.example.moneymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HelloController {
    public Button btnBeranda;
    public Button btnPendapatan;
    public Button btnPengeluaran;
    public Button btnInputTransaksi;
    public Button btnSaldo;
    public Label label1;
    public StackPane mainArea;
    @FXML
    private Label welcomeText;

    public void initialize() {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("beranda-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void goToBeranda(ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("beranda-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToPendapatan(ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("pendapatan-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToPengeluaran(ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("pengeluaran-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToInput(ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("input-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSaldo(ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("saldo-view.fxml"));
            mainArea.getChildren().removeAll();
            mainArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}