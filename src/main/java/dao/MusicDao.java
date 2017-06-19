package dao;

import entity.Music;
import java.util.*;
import org.hibernate.HibernateException;
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
        }finally {
            session.clear();
        }
    }
    public List<Music> searchMusic(String search){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Music> searchResult = new ArrayList<>();
        try{
            String hql = "FROM entity.Music m WHERE "
                    + "lower(m.song_name) LIKE lower('%" + search + "%') or "
                    + "lower(m.singer) LIKE lower('%" + search + "%')";
            searchResult = session.createQuery(hql).list();
        }catch(HibernateException e){
            System.err.println(e);
        }finally{
            session.close();
        }
        return searchResult;
    }

}