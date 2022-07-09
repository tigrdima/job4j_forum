package ru.job4j.forum.model;

import net.jcip.annotations.ThreadSafe;

import javax.persistence.*;
import java.util.Objects;

@ThreadSafe
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String eMail;
    private String password;

    public static User of(int id, String username, String eMail, String password) {
        User user = new User();
        user.id = id;
        user.username = username;
        user.eMail = eMail;
        user.password = password;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return eMail.equals(user.eMail) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eMail, password);
    }
}
