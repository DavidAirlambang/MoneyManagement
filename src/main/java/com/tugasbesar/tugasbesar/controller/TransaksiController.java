package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.SaldoDao;
import com.tugasbesar.tugasbesar.dao.TransaksiDao;
import com.tugasbesar.tugasbesar.dao.UserDao;
import com.tugasbesar.tugasbesar.model.*;
import com.tugasbesar.tugasbesar.utility.HiberUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    public Button printReport;

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

    public void printReport(ActionEvent actionEvent) throws JRException, SQLException {

        JasperPrint jasperPrint;
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/moneymanagementdb",
                "root",""
        );
        Map param = new HashMap();
        param.put("user",loggedIn());

        System.out.println(cmbFilter.getValue());
        if (cmbFilter.getValue() == "Pendapatan") {
            jasperPrint = JasperFillManager.fillReport("reports/LaporanPendapatan.jasper", param, conn);
        } else {
            jasperPrint = JasperFillManager.fillReport("reports/LaporanPengeluaran.jasper", param, conn);
        }
        JasperViewer viewer = new JasperViewer(jasperPrint,false);
        viewer.setTitle("Group Report");
        viewer.setVisible(true);

    }

    public String loggedIn(){

        String nama;
        BufferedReader reader;
        String filename = "data/logged.txt";
        try {
            reader = new BufferedReader(new FileReader(filename));
            String json = reader.readLine();
            nama = json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nama;
    }
}
