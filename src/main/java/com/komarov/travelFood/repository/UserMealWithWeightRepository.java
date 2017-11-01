package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface UserMealWithWeightRepository {
    UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int dayId, String menuName);

    boolean delete(int id, int dayId);

    UserMealWithWeight get(int id, int dayId);

    List<UserMealWithWeight> getAll(int dayId, String menuName);
}
