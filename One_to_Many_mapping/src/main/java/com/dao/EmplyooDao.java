package com.dao;

import com.vo.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmplyooDao {
    public void insert(Employee employee) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session =sessionFactory.openSession();
            Transaction transaction =session.beginTransaction();
            transaction.commit();
            session.save(employee);
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
