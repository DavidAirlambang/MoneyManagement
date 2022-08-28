package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.SaldoDao;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.dao.UserDao;
import com.tugasbesar.tugasbesar.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TransaksiController {
    public TextField inputIdTransaksi;
    public TextField inputNominal;
    public DatePicker inputTanggal;
    public ComboBox cmbJenis;
    public TextField inputKeterangan;
    public ComboBox cmbTempat;
    public Button btnSaveData;
    public TableView tabelTransaksi;
    public TableColumn columnNominal;
    public TableColumn columnTanggal;
    public TableColumn columnJenis;
    public TableColumn columnKeterangan;
    public TableColumn columnTempat;
    public ComboBox<String> cmbFilter;

    ObservableList<String> option;
    ObservableList<SaldoEntity> saldoData;
    private ObservableList<TransaksiEntity> transaksis;
    private ObservableList<UserEntity> users;

    public void initialize() {
        // Memasukkan option ke combo box pendapatan / pengeluaran
        option = FXCollections.observableArrayList("Pendapatan","Pengeluaran");
        cmbFilter.setItems(option);
        cmbFilter.getSelectionModel().select(0);

        // Memasukkan option ke combo box tempat penyimpanan saldo
        SaldoDao saldoDao = new SaldoDao();
        saldoData = FXCollections.observableArrayList(saldoDao.getData());
        cmbTempat.setItems(saldoData);
        cmbTempat.getSelectionModel().select(0);

        TransaksiDao transaksiDao = new TransaksiDao();
        transaksis = FXCollections.observableArrayList(transaksiDao.getData());
        tabelTransaksi.setItems(transaksis);
        columnNominal.setCellValueFactory(new PropertyValueFactory<>("nominalString"));
        columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        columnKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        columnTempat.setCellValueFactory(new PropertyValueFactory<>("saldoBySaldoIdSaldo"));
    }

    public void filterJenisData(ActionEvent actionEvent) {
        int selectedIndex = cmbFilter.getSelectionModel().getSelectedIndex();
        TransaksiDao transaksiDao = new TransaksiDao();
        if (selectedIndex == 0) {
            transaksis = FXCollections.observableArrayList(transaksiDao.getPendapatanData());
        } else if (selectedIndex == 1) {
            transaksis = FXCollections.observableArrayList(transaksiDao.getPengeluaranData());
        }
        tabelTransaksi.setItems(transaksis);
        columnNominal.setCellValueFactory(new PropertyValueFactory<>("nominalString"));
        columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        columnKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        columnTempat.setCellValueFactory(new PropertyValueFactory<>("saldoBySaldoIdSaldo"));
    }
}
