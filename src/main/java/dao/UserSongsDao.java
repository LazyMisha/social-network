package dao;

import entity.Music;
import entity.User_songs;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 07.06.17.
 */

public class UserSongsDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    MusicDao musicDao = new MusicDao();
    UserDao userDao = new UserDao();

    String SQL_QUERY_TO_GET_MUSIC = "select * from music where id in (select music_id from user_songs where user_id = ?)";

    public void save(User_songs user_songs){
        try {
            session.beginTransaction();
            session.save(user_songs);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }finally {
            session.clear();
        }
    }

    public ArrayList<Music> getByUserId(Long userId){
        ArrayList<Music> songs = new ArrayList<>();
        try{
            session.beginTransaction();
            Query getMusic = session.createSQLQuery(SQL_QUERY_TO_GET_MUSIC).setParameter(1, userId);
            List<Music> list = getMusic.list();
            for(Music music : list){
                    songs.add(music);
                }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return songs;
    }


}
