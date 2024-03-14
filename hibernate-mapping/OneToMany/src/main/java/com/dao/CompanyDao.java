package com.dao;

import com.vo.CompanyVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDao {
    public void insert(CompanyVo companyVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(companyVo);
        transaction.commit();
        session.close();
    }

    public void delete(CompanyVo companyVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(companyVo);
        transaction.commit();
        session.close();
    }

    public List<CompanyVo> findById(CompanyVo companyVo) {
        List<CompanyVo> findBy;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CompanyVo where id='"+companyVo.getId()+"'");
        findBy =query.list();
        return findBy;
    }

    public void update(CompanyVo companyVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(companyVo);
        transaction.commit();
        session.close();
    }
}
