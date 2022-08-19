package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;

@Entity
@Table(name = "pendapatan", schema = "moneymanagementdb", catalog = "")
public class PendapatanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPendapatan")
    private int idPendapatan;
    @Basic
    @Column(name = "jenisPendapatan")
    private String jenisPendapatan;

    public int getIdPendapatan() {
        return idPendapatan;
    }

    public void setIdPendapatan(int idPendapatan) {
        this.idPendapatan = idPendapatan;
    }

    public String getJenisPendapatan() {
        return jenisPendapatan;
    }

    public void setJenisPendapatan(String jenisPendapatan) {
        this.jenisPendapatan = jenisPendapatan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PendapatanEntity that = (PendapatanEntity) o;

        if (idPendapatan != that.idPendapatan) return false;
        if (jenisPendapatan != null ? !jenisPendapatan.equals(that.jenisPendapatan) : that.jenisPendapatan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPendapatan;
        result = 31 * result + (jenisPendapatan != null ? jenisPendapatan.hashCode() : 0);
        return result;
    }
}
