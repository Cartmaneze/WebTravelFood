package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Никита on 02.10.2017.
 */
@Table(name = "USERS")
public class User extends BaseEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
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

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
