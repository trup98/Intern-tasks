package oneToOne.Dao;

import oneToOne.Vo.RegisterVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class RegisterDao {
    public void insert(RegisterVo registerVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(registerVo);
        transaction.commit();
        session.close();
    }

    public List search() {
        List searchData;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from RegisterVo ");
        searchData = query.list();
        return searchData;
    }

    public void delete(RegisterVo registerVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(registerVo);
        transaction.commit();
        session.close();
    }

    public List<RegisterVo> findById(RegisterVo registerVo) {
        List<RegisterVo> findId;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from  RegisterVo where id='"+registerVo.getId()+"'");
        findId = query.list();
        return findId;
    }

    public void update(RegisterVo registerVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(registerVo);
        transaction.commit();
        session.close();
    }
}
