package dao;

import entity.User;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by misha on 06.05.17.
 */
public class UserDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public void save (User user){
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
