package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.PendapatanDao;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.model.PendapatanEntity;
import com.tugasbesar.tugasbesar.model.TransaksiEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PendapatanController {
    public TableView<TransaksiEntity> tabelPendapatan;
    public TableColumn kolomIdTransaksiPendapatan;
    public TableColumn kolomNominalPendapatan;
    public TableColumn kolomTanggalPendapatan;
    public TableColumn kolomJenisPendapatan;
    public TableColumn kolomKeteranganPendapatan;
    public TableColumn kolomTempatPendapatan;

    private ObservableList<PendapatanEntity> pendapatans;
    private ObservableList<TransaksiEntity> transaksis;

    public void initialize(){

        PendapatanDao pendapatanDao = new PendapatanDao();
        pendapatans = FXCollections.observableArrayList(pendapatanDao.getData());

        TransaksiDao transaksiDao = new TransaksiDao();
        transaksis = FXCollections.observableArrayList(transaksiDao.getData());
        System.out.println(transaksis);
        tabelPendapatan.setItems(transaksis);
        kolomIdTransaksiPendapatan.setCellValueFactory(new PropertyValueFactory<>("idTransaksi"));
        kolomNominalPendapatan.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        kolomTanggalPendapatan.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        kolomKeteranganPendapatan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        kolomJenisPendapatan.setCellValueFactory(new PropertyValueFactory<>("pendapatanByPendapatanIdPendapatan"));

    }

}
