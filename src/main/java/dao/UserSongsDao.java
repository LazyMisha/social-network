package dao;

import entity.Music;
import entity.User;
import entity.User_songs;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by misha on 07.06.17.
 */

public class UserSongsDao {

    private Session session = HibernateUtil.getSessionFactory().openSession();
    MusicDao musicDao = new MusicDao();
    UserDao userDao = new UserDao();

    String QUERY_TO_GET_MUSIC = "from Music where id in (select music from User_songs where user = ?)";

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

    public ArrayList<Music> getByUserId(User user){
        ArrayList<Music> songs = new ArrayList<>();
        List<Music> list = session.createQuery(QUERY_TO_GET_MUSIC).setParameter(0, user).list();
        for(Music music : list){
                songs.add(music);
            }
        return songs;
    }
}
