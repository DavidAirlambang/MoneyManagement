package com.tugasbesar.tugasbesar.dao;

import com.tugasbesar.tugasbesar.model.PendapatanEntity;
import com.tugasbesar.tugasbesar.model.PengeluaranEntity;
import com.tugasbesar.tugasbesar.model.TransaksiEntity;
import com.tugasbesar.tugasbesar.model.UserEntity;
import com.tugasbesar.tugasbesar.utility.HiberUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.validation.Validator;
import java.util.List;

public class UserDao implements DaoInterface<UserEntity> {
    @Override
    public List<UserEntity> getData() {
        List<UserEntity> userList;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(UserEntity.class);
        q.from(UserEntity.class);

        userList = s.createQuery(q).getResultList();

        s.close();
        return userList;
    }

    public int Validator(String username,String password){

        int hasil;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = q.from(UserEntity.class);

        Predicate p1 = builder.equal(root.get("username"),username);
        Predicate p2 = builder.equal(root.get("password"),password);
        Predicate p3 = builder.and(p1,p2);
        q.where(p3);

        List test = s.createQuery(q).getResultList();
        if(test.isEmpty()){
            hasil = 0;
        } else {
            hasil = 1;
        }

        s.close();

        return hasil;
    };

    public String UserLogged(String username) {
        String nama;

        SessionFactory sf = HiberUtility.getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery q = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = q.from(UserEntity.class);

        Predicate p1 = builder.equal(root.get("username"), username);
        q.where(p1);

        nama = String.valueOf(s.createQuery(q).getResultList());

        s.close();
        if (nama != null) {
            return nama;
        } else {
            return "";
        }
    }

    @Override
    public int addData(UserEntity data) {
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
    public int delData(UserEntity data) {
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
    public int updateData(UserEntity data) {
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
