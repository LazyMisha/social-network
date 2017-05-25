package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

/**
 * Created by misha on 06.05.17.
 * operations with user
 */
public class UserDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public void save(User user){
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            session.clear();
        }
    }

    public User isRegistered(String email, String password){
        List<User> usersList = session.createQuery("FROM User").list();
        for(User user : usersList){
            if((user.getEmail().equals(email))&&(user.getPassword().equals(password))){
                return user;
            }
        }
        return null;
    }

    public List<User> getFriends(){
        return session.createQuery("FROM User").list();
    }

    public User getUser(Long id){
        Query query=session.createQuery("FROM User WHERE id = ?").setParameter(0,id);
        User user=(User)query.uniqueResult();
        return user;
    }

    public void update(User user){
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            session.clear();
        }
    }

    public User getById(long id){
        Object user = null;
        try {
            session.beginTransaction();
            user = session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return (User) user;
    }
}