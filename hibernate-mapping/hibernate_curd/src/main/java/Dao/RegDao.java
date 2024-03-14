package Dao;

import Vo.RegVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RegDao {
    public void save(RegVo regVo) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(regVo);
        transaction.commit();
        session.close();
    }

    public List search() {
        List searchList = new ArrayList();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from RegVo ");

        searchList = query.list();
        session.close();
        return searchList;
    }

    public void delete(RegVo regVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(regVo);
        transaction.commit();
        session.close();
    }

    public List edit(RegVo regVo) {
        List editUserList = new ArrayList();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        Query query = session.createQuery("from RegVo where id ='"+regVo.getId()+"'");
        editUserList=query.list();
        return editUserList;
    }

    public void update(RegVo regVo) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session =factory.openSession();
        Transaction transaction =session.beginTransaction();
        session.update(regVo);
        transaction.commit();
        factory.close();
    }
}
