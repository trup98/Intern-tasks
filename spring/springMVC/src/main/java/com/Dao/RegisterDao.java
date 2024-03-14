package com.Dao;

import com.Model.RegisterVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterDao {
    @Autowired
    SessionFactory sessionFactory;

    public void insert(RegisterVo registerVo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(registerVo);
        transaction.commit();
        session.close();
    }

    public List search() {
        List searchData;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from RegisterVo ");
        searchData = query.list();
        return searchData;
    }

    public void delete(RegisterVo registerVo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(registerVo);
        transaction.commit();
        session.close();
    }

    public List<RegisterVo> edit(RegisterVo registerVo) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from RegisterVo where id='" + registerVo.getId() + "'");
        List<RegisterVo> editList = query.list();
        session.close();
        return editList;
    }
}
