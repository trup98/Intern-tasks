package com.dao;

import com.model.LoginVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDao {

    @Autowired
    SessionFactory sessionFactory;

    public void insert(LoginVo loginVo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(loginVo);
        transaction.commit();
        session.close();
    }

    public List<LoginVo> search() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from LoginVo ");
        List<LoginVo> loginVos = query.list();
        return loginVos;
    }

    public void delete(LoginVo loginVo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(loginVo);
        transaction.commit();
        session.close();
    }
}
