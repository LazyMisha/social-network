package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * create genre in database
 */

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name="genre")
public class Genre {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "genre", unique = false, nullable = true, length = 30)
    private String genre;

    @OneToMany
    @JoinColumn(name = "music_id", unique = false, nullable = true)
    private Set<Music> music_id = new HashSet<>();

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Music> getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Set<Music> music_id) {
        this.music_id = music_id;
    }
}