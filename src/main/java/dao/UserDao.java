package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
import util.SecurePassword;

/**
 * Created by misha on 06.05.17.
 * operations with user
 */
public class UserDao {

    String QUERY_MUSIC_SIZE = "select sum(size) from Music where id in (select music from User_songs where user = ?)";

    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.refresh(user);
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    public User isRegistered(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<User> usersList = session.createQuery("FROM entity.User WHERE email=" + "'" + email + "'").list();
            for (User user : usersList) {
                SecurePassword sp = new SecurePassword();
                if (sp.getSecurePassword(password, user.getSalt()).equals(user.getPassword()))
                    return user;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public List<User> getFriends() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            List<User> getFriends = session.createQuery("FROM entity.User").list();
            return getFriends;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public void update(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.refresh(user);
        }catch (Exception e){
            if( transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    public User getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            User user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            return user;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public String getMusicsSize(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery(QUERY_MUSIC_SIZE).setParameter(0, user);
            String getMusicsSize = query.uniqueResult().toString();
            return getMusicsSize;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }
    
    public String getPasswordHash(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT U.password FROM entity.User U WHERE email=" + "'" + email + "'";
            String passwordHash = (String) session.createQuery(hql).uniqueResult();
            return passwordHash;
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }
    
    public User getUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM entity.User WHERE email = ?").setParameter(0, email);
            User user = (User) query.uniqueResult();
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return null;
    }
}