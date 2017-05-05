package entity;

import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

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
    private long id;

    @Column(name = "age", unique = false, nullable = false)
    private int age;

    @Column(name = "first_name", unique = false, nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", unique = false, nullable = false, length = 100)
    private System email;

    public User() {

    }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }
}