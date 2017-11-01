package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.User;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceUser {
    User save(User meal);

    User get(int id);
}
