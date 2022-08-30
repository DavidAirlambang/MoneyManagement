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
    public TextField inputNominal;
    public DatePicker inputTanggal;
    public ComboBox cmbJenis;
    public TextField inputKeterangan;
    public ComboBox<SaldoEntity> cmbTempat;
    public Button btnSaveData;
    public TableView<TransaksiEntity> tabelTransaksi;
    public TableColumn columnNominal;
    public TableColumn columnTanggal;
    public TableColumn columnKeterangan;
    public TableColumn columnTempat;
    public ComboBox<String> cmbFilter;
    public Button printReport;
    public Button btnDeleteData;

    ObservableList<String> option;
    ObservableList jenis;
    ObservableList<SaldoEntity> saldoData;
    ObservableList<PendapatanEntity> pendapatanData;
    ObservableList<PengeluaranEntity> pengeluaranData;
    private ObservableList<TransaksiEntity> transaksis;
    private ObservableList<UserEntity> users;
    private UserEntity loggedUser;

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

        // Memasukkan option ke combo box tempat penyimpanan saldo
        SaldoDao saldoDao = new SaldoDao();
        saldoData = FXCollections.observableArrayList(saldoDao.getData());
        cmbTempat.setItems(saldoData);
        cmbTempat.getSelectionModel().select(0);

        UserDao userDao = new UserDao();
        users = FXCollections.observableArrayList(userDao.getData());
        String nama = userDao.UserLogged(loggedIn());
        users.forEach((u) -> {
            if (u.getUsername() == nama) {
                loggedUser = u;
            }
        });

        TransaksiDao transaksiDao = new TransaksiDao();
        transaksis = FXCollections.observableArrayList(transaksiDao.getData());
//        transaksis = FXCollections.observableArrayList(transaksiDao.getDataByUser(loggedUser));
        tabelTransaksi.setItems(transaksis);
        columnNominal.setCellValueFactory(new PropertyValueFactory<>("nominalString"));
        columnTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        columnKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        columnTempat.setCellValueFactory(new PropertyValueFactory<>("saldoBySaldoIdSaldo"));
        printReport.setDisable(true);
    }

    public void filterJenisData(ActionEvent actionEvent) {
        int selectedIndex = cmbFilter.getSelectionModel().getSelectedIndex();
        TransaksiDao transaksiDao = new TransaksiDao();
        if (selectedIndex == 0) {
            printReport.setDisable(true);
            transaksis = FXCollections.observableArrayList(transaksiDao.getData());
        } else if (selectedIndex == 1) {
            printReport.setDisable(false);
            transaksis = FXCollections.observableArrayList(transaksiDao.getPendapatanData());
            jenis = FXCollections.observableArrayList(pendapatanData);
            cmbJenis.setItems(jenis);
            cmbJenis.getSelectionModel().select(0);
        } else if (selectedIndex == 2) {
            printReport.setDisable(false);
            transaksis = FXCollections.observableArrayList(transaksiDao.getPengeluaranData());
            jenis = FXCollections.observableArrayList(pengeluaranData);
            cmbJenis.setItems(jenis);
            cmbJenis.getSelectionModel().select(0);
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
        if (cmbFilter.getSelectionModel().getSelectedIndex() == 1) {
            t.setPendapatanByPendapatanIdPendapatan((PendapatanEntity) cmbJenis.getValue());
        } else if (cmbFilter.getSelectionModel().getSelectedIndex() == 2) {
            t.setPengeluaranByPengeluaranIdPengeluaran((PengeluaranEntity) cmbJenis.getValue());
        }
        t.setSaldoBySaldoIdSaldo(cmbTempat.getValue());
        System.out.println(t);
        int hasil = dao.addData(t);
        transaksis = FXCollections.observableArrayList(dao.getData());
        tabelTransaksi.setItems(transaksis);
        if (hasil > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Berhasil Add Data", ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void deleteData(ActionEvent actionEvent) {
        TransaksiEntity selectedItems;
        selectedItems = tabelTransaksi.getSelectionModel().getSelectedItem();

        TransaksiDao dao = new TransaksiDao();

        Alert alertbox = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alertbox.showAndWait();
        if (alertbox.getResult() == ButtonType.OK) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
            alert.showAndWait();
            dao.delData(selectedItems);
        }
        transaksis = FXCollections.observableArrayList(dao.getData());
        tabelTransaksi.setItems(transaksis);
    }
}
