package com.dao;

import com.model.ProjectVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectDao {
    public List search() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ProjectVo ");
        List list = query.list();
        return list;
    }

    public void insert(ProjectVo projectVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(projectVo);
        transaction.commit();
        session.close();
        System.out.println("Data Inserted !!");
    }

    public void update(ProjectVo projectVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(projectVo);
        transaction.commit();
        session.close();
        System.out.println("Data Updated !!");
    }
}
