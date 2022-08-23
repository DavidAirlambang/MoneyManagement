package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.model.TransaksiEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PengeluaranController {
    public TableView tabelPengeluaran;
    public TableColumn kolomIdTransaksiPengeluaran;
    public TableColumn kolomNominalPengeluaran;
    public TableColumn kolomTanggalPengeluaran;
    public TableColumn kolomJenisPengeluaran;
    public TableColumn kolomKeteranganPengeluaran;
    public TableColumn kolomTempatPengeluaran;
    private ObservableList<TransaksiEntity> transaksis;
    public void initialize(){

        TransaksiDao transaksiDao = new TransaksiDao();
        transaksis = FXCollections.observableArrayList(transaksiDao.getPengeluaranData());
        tabelPengeluaran.setItems(transaksis);
        kolomIdTransaksiPengeluaran.setCellValueFactory(new PropertyValueFactory<>("idTransaksi"));
        kolomNominalPengeluaran.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        kolomTanggalPengeluaran.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        kolomKeteranganPengeluaran.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        kolomJenisPengeluaran.setCellValueFactory(new PropertyValueFactory<>("pengeluaranByPengeluaranIdPengeluaran"));
        kolomTempatPengeluaran.setCellValueFactory(new PropertyValueFactory<>("saldoBySaldoIdSaldo"));
    }

}
