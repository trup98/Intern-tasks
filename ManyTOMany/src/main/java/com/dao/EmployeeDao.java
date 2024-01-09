package com.dao;


import com.model.EmployeeVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    public List search() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from EmployeeVo ");
        List<EmployeeVo> list = query.list();
        return list;
    }

    public void insert(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employeeVo);
        transaction.commit();
        session.close();
        System.out.println("Data Inserted !!");
    }

    public List<EmployeeVo> edit(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from EmployeeVo where id='" + employeeVo.getId() + "'");
        List<EmployeeVo> list = query.list();
        return list;
    }

    public void update(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employeeVo);
        transaction.commit();
        session.close();
        System.out.println("Data Updated !!");
    }

    public void delete(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(employeeVo);
        transaction.commit();
        session.close();
        System.out.println("Data Deleted !!");
    }
}
