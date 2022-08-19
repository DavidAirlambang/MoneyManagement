package com.tugasbesar.tugasbesar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    public TextField inputIdTransaksi;
    public TextField inputNominal;
    public DatePicker inputTanggal;
    public ComboBox cmbJenis;
    public TextField inputKeterangan;
    public ComboBox cmbTempat;
    public Button btnSaveData;
    public TableView tabelPendapatan;
    public TableView tabelPengeluaran;
    public TableColumn kolomIdTransaksiPendapatan;
    public TableColumn kolomNominalPendapatan;
    public TableColumn kolomTanggalPendapatan;
    public TableColumn kolomJenisPendapatan;
    public TableColumn kolomKeteranganPendapatan;
    public TableColumn kolomTempatPendapatan;
    public TableColumn kolomIdTransaksiPengeluaran;
    public TableColumn kolomNominalPengeluaran;
    public TableColumn kolomTanggalPengeluaran;
    public TableColumn kolomJenisPengeluaran;
    public TableColumn kolomTempatPengeluaran;
    public TableColumn kolomKeteranganPengeluaran;
    public TextField txtTotalPengeluaran;
    public TextField txtTotalPendapatan;
    public AnchorPane layerBeranda;
    @FXML
    private Label welcomeText;

    public void initialize() {
//        Parent fxml = null;
//        try {
//            fxml = FXMLLoader.load(getClass().getResource("beranda-view.fxml"));
//            mainArea.getChildren().removeAll();
//            mainArea.getChildren().setAll(fxml);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void goToBeranda() {
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