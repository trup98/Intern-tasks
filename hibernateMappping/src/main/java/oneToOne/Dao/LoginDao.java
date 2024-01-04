package oneToOne.Dao;

import oneToOne.Vo.LoginVo;
import oneToOne.Vo.RegisterVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LoginDao {
    RegisterVo registerVo;
    public void insert(LoginVo loginVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(loginVo);
        transaction.commit();
        session.close();
    }

    public void delete(LoginVo loginId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(loginId);
        transaction.commit();
        session.close();
    }

    public void update(LoginVo loginVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(loginVo);
        transaction.commit();
        session.close();
    }
}
