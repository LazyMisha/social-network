package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name ="user_playlists")
public class User_playlists {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "playlist_name", unique = false, nullable = true, length = 64)
    private String playlist_name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user_id;

    @OneToMany
    @JoinColumn(name = "user_songs_id", nullable = true)
    private Set<User_songs> user_songs_id = new HashSet<>();

    public User_playlists() {
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

    public Set<User_songs> getUser_songs_id() {
        return user_songs_id;
    }

    public void setUser_songs_id(Set<User_songs> user_songs_id) {
        this.user_songs_id = user_songs_id;
    }
}