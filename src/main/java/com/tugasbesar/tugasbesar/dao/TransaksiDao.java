package com.tugasbesar.tugasbesar.dao;

import com.tugasbesar.tugasbesar.model.*;
import com.tugasbesar.tugasbesar.utility.HiberUtility;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TransaksiDao implements DaoInterface<TransaksiEntity> {
    @Override
    public List<TransaksiEntity> getData() {
        List<TransaksiEntity> transaksiList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        transaksiList = s.createQuery(q).getResultList();

        s.close();
        return transaksiList;
    }

    public List<TransaksiEntity> getPendapatanData() {
        List<TransaksiEntity> transaksiList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.isNotNull(root.get("pendapatanByPendapatanIdPendapatan"));
        q.where(p1);

        transaksiList = s.createQuery(q).getResultList();

        s.close();
        return transaksiList;
    }

    public int getJenisPendapatanData(int jenisPendapatan) {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.equal(root.get("pendapatanByPendapatanIdPendapatan"), jenisPendapatan);
        q.select(builder.sum(root.get("nominal"))).where(p1);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    public List<TransaksiEntity> getPengeluaranData() {
        List<TransaksiEntity> transaksiList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PengeluaranEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.isNotNull(root.get("pengeluaranByPengeluaranIdPengeluaran"));
        q.where(p1);

        transaksiList = s.createQuery(q).getResultList();

        s.close();
        return transaksiList;
    }

    public List<TransaksiEntity> getDataByUser(UserEntity user) {
        List<TransaksiEntity> transaksiList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<TransaksiEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.equal(root.get("UserByUserIdUser"), user);
        q.where(p1);

        transaksiList = s.createQuery(q).getResultList();

        s.close();
        return transaksiList;
    }

    public int getJenisPengeluaranData(int jenisPengeluaran) {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PengeluaranEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.equal(root.get("pengeluaranByPengeluaranIdPengeluaran"), jenisPengeluaran);
        q.select(builder.sum(root.get("nominal"))).where(p1);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    public int getSumPendapatanData() {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.isNotNull(root.get("pendapatanByPendapatanIdPendapatan"));
        q.select(builder.sum(root.get("nominal"))).where(p1);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    public int getSumPengeluaranData() {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.isNotNull(root.get("pengeluaranByPengeluaranIdPengeluaran"));
        q.select(builder.sum(root.get("nominal"))).where(p1);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    public int getSumTempatDataPendapatan(int idSaldo) {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<SaldoEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.equal(root.get("saldoBySaldoIdSaldo"), idSaldo);
        Predicate p2 = builder.isNotNull(root.get("pendapatanByPendapatanIdPendapatan"));
        Predicate p3 = builder.and(p1, p2);
        q.select(builder.sum(root.get("nominal"))).where(p3);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    public int getSumTempatDataPengeluaran(int idSaldo) {
        Integer total;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<SaldoEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.equal(root.get("saldoBySaldoIdSaldo"), idSaldo);
        Predicate p2 = builder.isNotNull(root.get("pengeluaranByPengeluaranIdPengeluaran"));
        Predicate p3 = builder.and(p1, p2);
        q.select(builder.sum(root.get("nominal"))).where(p3);

        total = (Integer) s.createQuery(q).getSingleResult();

        s.close();
        if (total != null) {
            return total;
        } else {
            return 0;
        }
    }

    @Override
    public int addData(TransaksiEntity data) {
        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.save(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int delData(TransaksiEntity data) {
        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.delete(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int updateData(TransaksiEntity data) {
        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        int hasil = 0;
        try {
            s.update(data);
            t.commit();
            hasil =  1;
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
        return hasil;
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
}
