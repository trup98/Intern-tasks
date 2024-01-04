package com.dao;

import com.vo.EmployeeVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeDao {
    public void insert(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        session.save(employeeVo);
        transaction.commit();
        session.close();
    }
}
