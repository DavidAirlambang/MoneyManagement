package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.PendapatanDao;
import com.tugasbesar.tugasbesar.dao.PengeluaranDao;
import com.tugasbesar.tugasbesar.dao.SaldoDao;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.model.PendapatanEntity;
import com.tugasbesar.tugasbesar.model.PengeluaranEntity;
import com.tugasbesar.tugasbesar.model.SaldoEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class SaldoController {
    public Text labelTotalBalance;
    public ComboBox cmbTempat;
    public Text labelSaldoTempat;
    public ComboBox cmbSaldoJenisPendapatan;
    public ComboBox cmbSaldoJenisPengeluaran;
    public Text labelSaldoJenisPendapatan;
    public Text labelSaldoJenisPengeluaran;
    public Text labelSaldoPendapatan;
    public Text labelSaldoPengeluaran;
    public Text labelSaldoNominalTempat;
    ObservableList<SaldoEntity> saldoData;
    ObservableList<PendapatanEntity> pendapatanData;
    ObservableList<PengeluaranEntity> pengeluaranData;
    public int sumPendapatan;
    public int sumPengeluaran;
    public int sumNominal;

    public void initialize() {
        // Memasukkan option ke combo box tempat penyimpanan saldo
        SaldoDao saldoDao = new SaldoDao();
        saldoData = FXCollections.observableArrayList(saldoDao.getData());
        cmbTempat.setItems(saldoData);
        cmbTempat.getSelectionModel().select(0);
        labelSaldoTempat.setText("");
        labelSaldoNominalTempat.setText("");

        // Memasukkan option ke combo box jenis pendapatan
        PendapatanDao pendapatanDao = new PendapatanDao();
        pendapatanData = FXCollections.observableArrayList(pendapatanDao.getData());
        cmbSaldoJenisPendapatan.setItems(pendapatanData);
        cmbSaldoJenisPendapatan.getSelectionModel().select(0);
        labelSaldoJenisPendapatan.setText("");
        labelSaldoPendapatan.setText("");

        // Memasukkan option ke combo box jenis pengeluaran
        PengeluaranDao pengeluaranDao = new PengeluaranDao();
        pengeluaranData = FXCollections.observableArrayList(pengeluaranDao.getData());
        cmbSaldoJenisPengeluaran.setItems(pengeluaranData);
        cmbSaldoJenisPengeluaran.getSelectionModel().select(0);
        labelSaldoJenisPengeluaran.setText("");
        labelSaldoPengeluaran.setText("");

        // Menampilkan total balance
        TransaksiDao transaksiDao = new TransaksiDao();
        sumPendapatan = transaksiDao.getSumPendapatanData();
        sumPengeluaran = transaksiDao.getSumPengeluaranData();
        sumNominal = sumPendapatan - sumPengeluaran;
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        labelTotalBalance.setText(String.valueOf(cFormatter.format(sumNominal)));
    }

    public void getChosenTempat(ActionEvent actionEvent) {
        // Menampilkan tempat penyimpanan saldo
        int selectedIndex = cmbTempat.getSelectionModel().getSelectedIndex();
        String selectedTempat = String.valueOf(cmbTempat.getSelectionModel().getSelectedItem());
        labelSaldoTempat.setText(selectedTempat);
        // Menampilkan nominal uang yang disimpan di tempat tertentu
        SaldoDao saldoDao = new SaldoDao();
        int selectedId = saldoData.get(selectedIndex).getIdSaldo();
        TransaksiDao transaksiDao = new TransaksiDao();
        sumPendapatan = transaksiDao.getSumTempatDataPendapatan(selectedId);
        sumPengeluaran = transaksiDao.getSumTempatDataPengeluaran(selectedId);
        sumNominal = sumPendapatan - sumPengeluaran;
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        labelSaldoNominalTempat.setText(String.valueOf(cFormatter.format(sumNominal)));
    }

    public void getChosenJenisPendapatan(ActionEvent actionEvent) {
        // Menampilkan jenis pendapatan
        int selectedIndex = cmbSaldoJenisPendapatan.getSelectionModel().getSelectedIndex();
        String selectedJenisPendapatan = String.valueOf(cmbSaldoJenisPendapatan.getSelectionModel().getSelectedItem());
        labelSaldoJenisPendapatan.setText(selectedJenisPendapatan);
        // Menampilkan nominal pengeluaran jenis tertentu
        PendapatanDao pendapatanDao = new PendapatanDao();
        int selectedId = pendapatanData.get(selectedIndex).getIdPendapatan();
        TransaksiDao transaksiDao = new TransaksiDao();
        sumNominal = transaksiDao.getJenisPendapatanData(selectedId);
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        labelSaldoPendapatan.setText(String.valueOf(cFormatter.format(sumNominal)));
    }

    public void getChosenJenisPengeluaran(ActionEvent actionEvent) {
        // Menampilkan jenis pengeluaran
        int selectedIndex = cmbSaldoJenisPengeluaran.getSelectionModel().getSelectedIndex();
        String selectedJenisPengeluaran = String.valueOf(cmbSaldoJenisPengeluaran.getSelectionModel().getSelectedItem());
        labelSaldoJenisPengeluaran.setText(selectedJenisPengeluaran);
        // Menampilkan nominal pengeluaran jenis tertentu
        PengeluaranDao pengeluaranDao = new PengeluaranDao();
        int selectedId = pengeluaranData.get(selectedIndex).getIdPengeluaran();
        TransaksiDao transaksiDao = new TransaksiDao();
        sumNominal = transaksiDao.getJenisPengeluaranData(selectedId);
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        labelSaldoPengeluaran.setText(String.valueOf(cFormatter.format(sumNominal)));
    }
}
