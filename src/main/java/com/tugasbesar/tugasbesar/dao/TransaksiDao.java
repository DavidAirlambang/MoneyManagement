package com.tugasbesar.tugasbesar.dao;

import com.tugasbesar.tugasbesar.model.PendapatanEntity;
import com.tugasbesar.tugasbesar.model.TransaksiEntity;
import com.tugasbesar.tugasbesar.utility.HiberUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<TransaksiEntity> getPengeluaranData() {
        List<TransaksiEntity> transaksiList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(TransaksiEntity.class);
        Root<PendapatanEntity> root = q.from(TransaksiEntity.class);

        Predicate p1 = builder.isNotNull(root.get("pengeluaranByPengeluaranIdPengeluaran"));
        q.where(p1);

        transaksiList = s.createQuery(q).getResultList();

        s.close();
        return transaksiList;
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
}
