package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name="music")
public class Music {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "song_name", unique = false, nullable = true, length = 40)
    private String song_name;

    @Column(name = "date", unique = false, nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @OneToMany
    @JoinColumn(name = "singer_id", nullable = true)
    private Set<Singer> singer_id = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "genre_id", unique = false, nullable = true)
    private Genre genre_id;

    @Column(name = "size", unique = false, nullable = true, length = 50)
    private Integer size;

    public Music() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Set<Singer> getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Set<Singer> singer_id) {
        this.singer_id = singer_id;
    }

    public Genre getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Genre genre_id) {
        this.genre_id = genre_id;
    }
}