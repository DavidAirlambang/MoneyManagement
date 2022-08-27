package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransaksiEntity that = (TransaksiEntity) o;

        if (idTransaksi != null ? !idTransaksi.equals(that.idTransaksi) : that.idTransaksi != null) return false;
        if (nominal != null ? !nominal.equals(that.nominal) : that.nominal != null) return false;
        if (tanggalTransaksi != null ? !tanggalTransaksi.equals(that.tanggalTransaksi) : that.tanggalTransaksi != null)
            return false;
        if (keterangan != null ? !keterangan.equals(that.keterangan) : that.keterangan != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransaksi != null ? idTransaksi.hashCode() : 0;
        result = 31 * result + (nominal != null ? nominal.hashCode() : 0);
        result = 31 * result + (tanggalTransaksi != null ? tanggalTransaksi.hashCode() : 0);
        result = 31 * result + (keterangan != null ? keterangan.hashCode() : 0);
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

    public String getNominalString() {
        NumberFormat cFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return cFormatter.format(getNominal());
    }

    @Override
    public String toString() {
        return "TransaksiEntity{" +
                "idTransaksi='" + idTransaksi + '\'' +
                ", nominal=" + nominal +
                ", tanggalTransaksi=" + tanggalTransaksi +
                ", keterangan='" + keterangan + '\'' +
                ", userByUserIdUser=" + userByUserIdUser +
                ", saldoBySaldoIdSaldo=" + saldoBySaldoIdSaldo +
                ", pendapatanByPendapatanIdPendapatan=" + pendapatanByPendapatanIdPendapatan +
                ", pengeluaranByPengeluaranIdPengeluaran=" + pengeluaranByPengeluaranIdPengeluaran +
                '}';
    }
}
