package com.dao;

import com.vo.CompanyVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CompanyDao {
    public void insert(CompanyVo companyVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        session.save(companyVo);
        transaction.commit();
        session.close();
    }
}
