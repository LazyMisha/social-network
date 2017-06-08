package dao;

import entity.Music;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
    public ArrayList<Music> searchMusic(String search){
        
        String searchPattern=".*"+search+".*";
        
        ArrayList<Music> musicArr=new ArrayList();
        List<Music> musicList=session.createQuery("FROM Music").list();
        
        for(Music m:musicList){
            if(m.getSong_name().matches(searchPattern)||m.getSinger().matches(searchPattern)){
                musicArr.add(m);
            }
        }
        return musicArr;
    }

}