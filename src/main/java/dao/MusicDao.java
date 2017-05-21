package dao;

import entity.Music;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by misha on 20.05.17.
 * manage music files
 */
public class MusicDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();

    public void save(Music music){
        try {
            session.beginTransaction();
            session.save(music);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}