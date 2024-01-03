package oneToOne.Dao;

import oneToOne.Vo.LoginVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LoginDao {
    public void insert(LoginVo loginVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(loginVo);
        transaction.commit();
        session.close();
    }

    public void delete(LoginVo loginVo) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(loginVo);
        transaction.commit();
        session.close();
    }
}
