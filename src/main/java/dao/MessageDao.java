package dao;

import entity.Message;
import entity.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * @author Stepanyuk
 */

public class MessageDao {
    public void sendMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        }catch(Exception e){
            if( transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
        finally{
            session.close();
        }
    }
    
    public List<Message> getDialog(User user, User friend){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q=session.createQuery
        ("FROM entity.Message WHERE user_id_from = ? AND user_id_to = ? " + 
                "or user_id_from = ? AND user_id_to = ?")
                .setParameter(0, user).setParameter(1, friend).setParameter(2, friend).setParameter(3, user);
        return q.list();
    }
}