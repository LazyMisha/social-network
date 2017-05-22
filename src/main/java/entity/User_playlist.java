package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * create playlist in database
 */

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name ="user_playlist")
public class User_playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "playlist_name")
    private String playlist_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToMany
    @JoinColumn(name = "music_id")
    private Set<Music> music = new HashSet<>();

    public User_playlist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Set<Music> getMusic() {
        return music;
    }

    public void setMusic(Set<Music> music) {
        this.music = music;
    }
}