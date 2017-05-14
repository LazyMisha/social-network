package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name="path")
public class Path {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name="path",unique = false, nullable = true)
    private String path;

    @OneToOne
    @JoinColumn(name = "music_id", nullable = true)
    private Music music_id;

    public Path() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Music getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Music music) {
        this.music_id = music_id;
    }
}