package dao;

import entity.User;
import org.hibernate.Session;
import util.HibernateUtil;
import java.util.List;

/**
 * Created by misha on 06.05.17.
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
        }
    }
    
    public User isRegistered(String email,String password){

        List<User> usersList=session.createQuery("FROM User").list();
        for(User u:usersList){
            if((u.getEmail().equals(email))&&(u.getPassword().equals(password))){
                return u;
            }
        }
        return null;
    }
}