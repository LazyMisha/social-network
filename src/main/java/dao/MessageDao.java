package dao;

import entity.Message;
import entity.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class MessageDao {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    
    public void sendMessage(Message message){
        try{
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        finally{
            session.clear();
        }
    }
    
    public List<Message> getDialog(User user, User friend){
        Query q=session.createQuery
        ("FROM entity.Message WHERE user_id_from = ? AND user_id_to = ? "
                + "or user_id_from = ? AND user_id_to = ?")
                .setParameter(0, user).setParameter(1, friend).setParameter(2, friend).setParameter(3, user);
        
        return q.list();
    }
}
