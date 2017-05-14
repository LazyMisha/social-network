package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import java.util.Date;

/**
 * create user in database
 */

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", unique = false, nullable = true, length = 64)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = true, length = 64)
    private String lastName;

    @Column(name = "email", unique = false, nullable = true, length = 64)
    private String email;

    @Column(name = "password", nullable = true, length = 64)
    private String password;

    @Column(name = "date_of_birthday", unique = false, nullable = true)
    private Date birthday;

    @Column(name = "user_info", nullable = true, unique = false, length = 256)
    private String user_info;

    @Column(name = "registration_date", unique = false, nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date registration_date;

    @ManyToOne
    @JoinColumn(name = "city_id", unique = false, nullable = true)
    private City city_id;

    @Column(name = "is_deleted", unique = false, nullable = true)
    private Boolean is_deleted;

    @Column(name = "path_to_photo", unique = false, nullable = true)
    private String path_to_photo;

    public User(){
    }

    public User(Long id){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public City getCity_id() {
        return city_id;
    }

    public void setCity_id(City city_id) {
        this.city_id = city_id;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getPath_to_photo() {
        return path_to_photo;
    }

    public void setPath_to_photo(String path_to_photo) {
        this.path_to_photo = path_to_photo;
    }
}