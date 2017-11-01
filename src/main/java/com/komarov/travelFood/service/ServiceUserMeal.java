package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.UserMeal;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceUserMeal {
    UserMeal saveOrUpdate(UserMeal meal, int userId);

    boolean delete(int id, int userId);

    UserMeal get(int id, int userId);

    List<UserMeal> getAll(int userId);
}
