package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Никита on 02.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.BY_LOGIN_PASSWORD, query = "SELECT DISTINCT u FROM User u WHERE u.login=:login AND u.password=:password"),
        @NamedQuery(name = User.BY_LOGIN, query = "SELECT DISTINCT u FROM User u WHERE u.login=:login")
})
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
    public static final String BY_LOGIN_PASSWORD = "User.getByLoginPassword";
    public static final String BY_LOGIN = "User.getByLogin";

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Journey> journeyList;

    public User() {
    }

    public User(int id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Journey> getJourneyList() {
        return journeyList;
    }

    public void setJourneyList(List<Journey> journeyList) {
        this.journeyList = journeyList;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
