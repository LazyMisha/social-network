package entity;

import java.util.Date;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

/**
 * create message in database
 */

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id_from")
    private User user_id_from;

    @ManyToOne
    @JoinColumn(name = "user_id_to")
    private User user_id_to;

    @Column(name = "is_read")
    private Boolean is_read;
    
    @Column(name = "messageDate")
    private Date messageDate;
    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(User user_id_from) {
        this.user_id_from = user_id_from;
    }

    public User getUser_id_to() {
        return user_id_to;
    }
    
    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public void setUser_id_to(User user_id_to) {
        this.user_id_to = user_id_to;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }
}