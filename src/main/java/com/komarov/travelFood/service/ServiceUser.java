package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.User;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceUser {
    User save(User user);

    User get(int id);

    User getByLoginPassword(String login, String password);

    User getByLogin(String login);
}
