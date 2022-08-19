package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transaksi", schema = "moneymanagementdb", catalog = "")
public class TransaksiEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTransaksi")
    private String idTransaksi;
    @Basic
    @Column(name = "nominal")
    private Integer nominal;
    @Basic
    @Column(name = "tanggalTransaksi")
    private Date tanggalTransaksi;
    @Basic
    @Column(name = "keterangan")
    private String keterangan;
    @Basic
    @Column(name = "user_idUser")
    private int userIdUser;
    @Basic
    @Column(name = "saldo_idSaldo")
    private int saldoIdSaldo;
    @Basic
    @Column(name = "pendapatan_idPendapatan")
    private Integer pendapatanIdPendapatan;
    @Basic
    @Column(name = "pengeluaran_idPengeluaran")
    private Integer pengeluaranIdPengeluaran;
    @ManyToOne
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser", nullable = false)
    private UserEntity userByUserIdUser;
    @ManyToOne
    @JoinColumn(name = "saldo_idSaldo", referencedColumnName = "idSaldo", nullable = false)
    private SaldoEntity saldoBySaldoIdSaldo;
    @ManyToOne
    @JoinColumn(name = "pendapatan_idPendapatan", referencedColumnName = "idPendapatan")
    private PendapatanEntity pendapatanByPendapatanIdPendapatan;
    @ManyToOne
    @JoinColumn(name = "pengeluaran_idPengeluaran", referencedColumnName = "idPengeluaran")
    private PengeluaranEntity pengeluaranByPengeluaranIdPengeluaran;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    public int getSaldoIdSaldo() {
        return saldoIdSaldo;
    }

    public void setSaldoIdSaldo(int saldoIdSaldo) {
        this.saldoIdSaldo = saldoIdSaldo;
    }

    public Integer getPendapatanIdPendapatan() {
        return pendapatanIdPendapatan;
    }

    public void setPendapatanIdPendapatan(Integer pendapatanIdPendapatan) {
        this.pendapatanIdPendapatan = pendapatanIdPendapatan;
    }

    public Integer getPengeluaranIdPengeluaran() {
        return pengeluaranIdPengeluaran;
    }

    public void setPengeluaranIdPengeluaran(Integer pengeluaranIdPengeluaran) {
        this.pengeluaranIdPengeluaran = pengeluaranIdPengeluaran;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransaksiEntity that = (TransaksiEntity) o;

        if (userIdUser != that.userIdUser) return false;
        if (saldoIdSaldo != that.saldoIdSaldo) return false;
        if (idTransaksi != null ? !idTransaksi.equals(that.idTransaksi) : that.idTransaksi != null) return false;
        if (nominal != null ? !nominal.equals(that.nominal) : that.nominal != null) return false;
        if (tanggalTransaksi != null ? !tanggalTransaksi.equals(that.tanggalTransaksi) : that.tanggalTransaksi != null)
            return false;
        if (keterangan != null ? !keterangan.equals(that.keterangan) : that.keterangan != null) return false;
        if (pendapatanIdPendapatan != null ? !pendapatanIdPendapatan.equals(that.pendapatanIdPendapatan) : that.pendapatanIdPendapatan != null)
            return false;
        if (pengeluaranIdPengeluaran != null ? !pengeluaranIdPengeluaran.equals(that.pengeluaranIdPengeluaran) : that.pengeluaranIdPengeluaran != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransaksi != null ? idTransaksi.hashCode() : 0;
        result = 31 * result + (nominal != null ? nominal.hashCode() : 0);
        result = 31 * result + (tanggalTransaksi != null ? tanggalTransaksi.hashCode() : 0);
        result = 31 * result + (keterangan != null ? keterangan.hashCode() : 0);
        result = 31 * result + userIdUser;
        result = 31 * result + saldoIdSaldo;
        result = 31 * result + (pendapatanIdPendapatan != null ? pendapatanIdPendapatan.hashCode() : 0);
        result = 31 * result + (pengeluaranIdPengeluaran != null ? pengeluaranIdPengeluaran.hashCode() : 0);
        return result;
    }

    public UserEntity getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(UserEntity userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    public SaldoEntity getSaldoBySaldoIdSaldo() {
        return saldoBySaldoIdSaldo;
    }

    public void setSaldoBySaldoIdSaldo(SaldoEntity saldoBySaldoIdSaldo) {
        this.saldoBySaldoIdSaldo = saldoBySaldoIdSaldo;
    }

    public PendapatanEntity getPendapatanByPendapatanIdPendapatan() {
        return pendapatanByPendapatanIdPendapatan;
    }

    public void setPendapatanByPendapatanIdPendapatan(PendapatanEntity pendapatanByPendapatanIdPendapatan) {
        this.pendapatanByPendapatanIdPendapatan = pendapatanByPendapatanIdPendapatan;
    }

    public PengeluaranEntity getPengeluaranByPengeluaranIdPengeluaran() {
        return pengeluaranByPengeluaranIdPengeluaran;
    }

    public void setPengeluaranByPengeluaranIdPengeluaran(PengeluaranEntity pengeluaranByPengeluaranIdPengeluaran) {
        this.pengeluaranByPengeluaranIdPengeluaran = pengeluaranByPengeluaranIdPengeluaran;
    }
}
