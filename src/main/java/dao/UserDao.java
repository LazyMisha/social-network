package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import util.SecurePassword;

/**
 * Created by misha on 06.05.17.
 * operations with user
 */
public class UserDao {

    String QUERY_MUSIC_SIZE = "select sum(size) from Music where id in (select music from User_songs where user = ?)";

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
            if(user.getEmail().equals(email)){
                SecurePassword sp=new SecurePassword();
                if(sp.getSecurePassword(password, user.getSalt()).equals(user.getPassword()))
                    return user;
            }
        }
        return null;
    }

    public List<User> getFriends(){
        return session.createQuery("FROM User").list();
    }

    public void update(User user){
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.refresh(user);
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
        }finally {
            session.clear();
        }
        return (User) user;
    }

    public String getMusicsSize(User user){
        Query query = session.createQuery(QUERY_MUSIC_SIZE).setParameter(0, user);
        return query.uniqueResult().toString();
    }
    
    public String getPasswordHash(String email){
        List<User> usersList = session.createQuery("FROM User").list();
        for(User user : usersList){
            if(user.getEmail().equals(email)){
                String passwordHash=user.getPassword();
                return passwordHash;
            }
        }
        return null;
    }
    
    public User getUserByEmail(String email){
        Query query=session.createQuery("FROM User WHERE email = ?").setParameter(0,email);
        User user=(User)query.uniqueResult();
        return user;
    }
}