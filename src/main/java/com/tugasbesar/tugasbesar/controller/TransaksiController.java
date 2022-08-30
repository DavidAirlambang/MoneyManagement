package com.tugasbesar.tugasbesar.controller;

import com.tugasbesar.tugasbesar.dao.*;
import com.tugasbesar.tugasbesar.model.*;
import com.tugasbesar.tugasbesar.utility.HiberUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

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
    ObservableList jenis;
    ObservableList<SaldoEntity> saldoData;
    ObservableList<PendapatanEntity> pendapatanData;
    ObservableList<PengeluaranEntity> pengeluaranData;
    private ObservableList<TransaksiEntity> transaksis;
    private ObservableList<UserEntity> users;

    private FilteredList<UserEntity> filteredUser;

    public void initialize() {
        // Memasukkan option ke combo box pendapatan / pengeluaran
        option = FXCollections.observableArrayList("All","Pendapatan", "Pengeluaran");
        cmbFilter.setItems(option);
        cmbFilter.getSelectionModel().select(0);

        // Memasukkan option ke combo box jenis
        PendapatanDao pendapatanDao = new PendapatanDao();
        PengeluaranDao pengeluaranDao = new PengeluaranDao();
        pendapatanData = FXCollections.observableArrayList(pendapatanDao.getData());
        pengeluaranData = FXCollections.observableArrayList(pengeluaranDao.getData());
        //        jenis = FXCollections.observableArrayList(pendapatanData, pengeluaranData);

        // Memasukkan option ke combo box tempat penyimpanan saldo
        SaldoDao saldoDao = new SaldoDao();
        saldoData = FXCollections.observableArrayList(saldoDao.getData());
        cmbTempat.setItems(saldoData);
        cmbTempat.getSelectionModel().select(0);

        UserDao userDao = new UserDao();
        String nama = userDao.UserLogged(loggedIn());

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
            printReport.setDisable(true);
            transaksis = FXCollections.observableArrayList(transaksiDao.getData());
        } else if (selectedIndex == 1) {
            printReport.setDisable(false);
            transaksis = FXCollections.observableArrayList(transaksiDao.getPengeluaranData());
            pengeluaranData.forEach((i) -> {
                jenis = FXCollections.observableArrayList(pengeluaranData);
            });
            cmbJenis.setItems(jenis);
        } else if (selectedIndex == 2) {
            printReport.setDisable(false);
            transaksis = FXCollections.observableArrayList(transaksiDao.getPendapatanData());
            pengeluaranData.forEach((i) -> {
                jenis = FXCollections.observableArrayList(pengeluaranData);
            });
            cmbJenis.setItems(jenis);
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
                "root", ""
        );
        Map param = new HashMap();
        param.put("user", loggedIn());
        if (cmbFilter.getValue() == "Pendapatan") {
            jasperPrint = JasperFillManager.fillReport("reports/LaporanPendapatan.jasper", param, conn);
        } else {
            jasperPrint = JasperFillManager.fillReport("reports/LaporanPengeluaran.jasper", param, conn);
        }
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Group Report");
        viewer.setVisible(true);

    }

    public String loggedIn() {

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

    public void saveData(ActionEvent actionEvent) {
        TransaksiDao dao = new TransaksiDao();
        TransaksiEntity t = new TransaksiEntity();
        t.setNominal(Integer.valueOf(inputNominal.getText()));
        t.setTanggalTransaksi(Date.valueOf(inputTanggal.getValue()));
        t.setKeterangan(inputKeterangan.getText());
        int hasil = dao.addData(t);
        transaksis = FXCollections.observableArrayList(dao.getData());
        tabelTransaksi.setItems(transaksis);
        if (hasil > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Berhasil Add Data", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
