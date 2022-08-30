package com.tugasbesar.tugasbesar.controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.tugasbesar.tugasbesar.HelloApplication;
import com.tugasbesar.tugasbesar.MenuController;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardController {
    public Label username;
    public Label totalBalance;
    public Label pendapatan;
    public Label pengeluaran;
    public JFXButton printReport;

    public int sumPendapatan;
    public int sumPengeluaran;
    public int sumNominal;
    public Label tanggal;
    public Button signOut;

    public void initialize() {
        // Menampilkan Total Balance
        TransaksiDao transaksiDao = new TransaksiDao();
        sumPendapatan = transaksiDao.getSumPendapatanData();
        sumPengeluaran = transaksiDao.getSumPengeluaranData();
        sumNominal = sumPendapatan - sumPengeluaran;
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        totalBalance.setText(String.valueOf(cFormatter.format(sumNominal)));
        pendapatan.setText(String.valueOf(cFormatter.format(sumPendapatan)));
        pengeluaran.setText(String.valueOf(cFormatter.format(sumPengeluaran)));
        loggedIn();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String format = formatter.format(date);
        tanggal.setText(format);
    }

    public void printReport(ActionEvent actionEvent) {
    }

    public void loggedIn(){
        BufferedReader reader;
        String filename = "data/logged.txt";
        try {
            reader = new BufferedReader(new FileReader(filename));
            String json = reader.readLine();
            username.setText(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        username.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),640, 430);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
