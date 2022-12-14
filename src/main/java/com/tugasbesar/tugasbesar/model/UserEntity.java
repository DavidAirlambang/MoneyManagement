package com.tugasbesar.tugasbesar.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "moneymanagementdb", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "userByUserIdUser")
    private Collection<TransaksiEntity> transaksisByIdUser;
    @Basic
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    public Collection<TransaksiEntity> getTransaksisByIdUser() {
        return transaksisByIdUser;
    }

    public void setTransaksisByIdUser(Collection<TransaksiEntity> transaksisByIdUser) {
        this.transaksisByIdUser = transaksisByIdUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
