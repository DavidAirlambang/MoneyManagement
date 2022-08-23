package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.SaldoDao;
import com.tugasbesar.tugasbesar.model.SaldoEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InputController {

    public TextField inputIdTransaksi;
    public TextField inputNominal;
    public DatePicker inputTanggal;
    public ComboBox cmbJenis;
    public TextField inputKeterangan;
    public ComboBox cmbTempat;
    public Button btnSaveData;
    public ComboBox cmbPendapatanPengeluaran;
    ObservableList<String> option;
    ObservableList<SaldoEntity> saldoData;

    public void initialize() {
        // Memasukkan option ke combo box pendapatan / pengeluaran
        option = FXCollections.observableArrayList("Pendapatan","Pengeluaran");
        cmbPendapatanPengeluaran.setItems(option);
        cmbPendapatanPengeluaran.getSelectionModel().select(0);

        // Memasukkan option ke combo box tempat penyimpanan saldo
        SaldoDao saldoDao = new SaldoDao();
        saldoData = FXCollections.observableArrayList(saldoDao.getData());
        cmbTempat.setItems(saldoData);


    }
}
