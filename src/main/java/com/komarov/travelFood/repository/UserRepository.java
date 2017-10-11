package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.User;

/**
 * Created by Никита on 11.10.2017.
 */
public interface UserRepository {
    User save(User meal);

    User get(int id);
}
