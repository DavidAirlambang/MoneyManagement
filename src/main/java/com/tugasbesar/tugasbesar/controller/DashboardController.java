package com.tugasbesar.tugasbesar.controller;

import com.jfoenix.controls.JFXButton;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.text.NumberFormat;
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
    }

    public void printReport(ActionEvent actionEvent) {
    }
}
