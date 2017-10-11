package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface UserMealWithWeightRepository {
    UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int userId);

    boolean delete(int id, int userId);

    UserMealWithWeight get(int id, int userId);

    List<UserMealWithWeight> getAll(int userId);
}
