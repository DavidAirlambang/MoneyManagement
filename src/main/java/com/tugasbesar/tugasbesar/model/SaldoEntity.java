package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "saldo", schema = "moneymanagementdb", catalog = "")
public class SaldoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSaldo")
    private int idSaldo;
    @Basic
    @Column(name = "jenisSaldo")
    private String jenisSaldo;
    @OneToMany(mappedBy = "saldoBySaldoIdSaldo")
    private Collection<TransaksiEntity> transaksisByIdSaldo;

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public String getJenisSaldo() {
        return jenisSaldo;
    }

    public void setJenisSaldo(String jenisSaldo) {
        this.jenisSaldo = jenisSaldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaldoEntity that = (SaldoEntity) o;

        if (idSaldo != that.idSaldo) return false;
        if (jenisSaldo != null ? !jenisSaldo.equals(that.jenisSaldo) : that.jenisSaldo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSaldo;
        result = 31 * result + (jenisSaldo != null ? jenisSaldo.hashCode() : 0);
        return result;
    }

    public Collection<TransaksiEntity> getTransaksisByIdSaldo() {
        return transaksisByIdSaldo;
    }

    public void setTransaksisByIdSaldo(Collection<TransaksiEntity> transaksisByIdSaldo) {
        this.transaksisByIdSaldo = transaksisByIdSaldo;
    }

    @Override
    public String toString() {
        return jenisSaldo;
    }
}
