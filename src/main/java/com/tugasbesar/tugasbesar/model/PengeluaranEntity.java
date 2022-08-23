package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pengeluaran", schema = "moneymanagementdb", catalog = "")
public class PengeluaranEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPengeluaran")
    private int idPengeluaran;
    @Basic
    @Column(name = "jenisPengeluaran")
    private String jenisPengeluaran;
    @OneToMany(mappedBy = "pengeluaranByPengeluaranIdPengeluaran")
    private Collection<TransaksiEntity> transaksisByIdPengeluaran;

    @Override
    public String toString() {
        return jenisPengeluaran;
    }

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public String getJenisPengeluaran() {
        return jenisPengeluaran;
    }

    public void setJenisPengeluaran(String jenisPengeluaran) {
        this.jenisPengeluaran = jenisPengeluaran;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PengeluaranEntity that = (PengeluaranEntity) o;

        if (idPengeluaran != that.idPengeluaran) return false;
        if (jenisPengeluaran != null ? !jenisPengeluaran.equals(that.jenisPengeluaran) : that.jenisPengeluaran != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPengeluaran;
        result = 31 * result + (jenisPengeluaran != null ? jenisPengeluaran.hashCode() : 0);
        return result;
    }

    public Collection<TransaksiEntity> getTransaksisByIdPengeluaran() {
        return transaksisByIdPengeluaran;
    }

    public void setTransaksisByIdPengeluaran(Collection<TransaksiEntity> transaksisByIdPengeluaran) {
        this.transaksisByIdPengeluaran = transaksisByIdPengeluaran;
    }
}
