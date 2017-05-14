package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

/**
 * create singer in database
 */

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "singer")
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "singer", unique = false, nullable = true)
    private String singer;

    public Singer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}