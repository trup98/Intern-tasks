package com.dao;

import com.vo.EmployeeVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    public void insert(List<EmployeeVo> employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        employeeVo.forEach(session::save);
        transaction.commit();
        session.close();
    }

    public List<EmployeeVo> search() {
        List voList;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from EmployeeVo ");
        voList = query.list();
        return voList;
    }

    public void update(EmployeeVo employeeVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employeeVo);
        transaction.commit();
        session.close();
    }
}
