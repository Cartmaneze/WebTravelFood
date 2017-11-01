package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.UserMeal;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface UserMealRepository {

    UserMeal saveOrUpdate(UserMeal meal, int userId);

    boolean delete(int id, int userId);

    UserMeal get(int id, int userId);

    List<UserMeal> getAll(int userId);
}
