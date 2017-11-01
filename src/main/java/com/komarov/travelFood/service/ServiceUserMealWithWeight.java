package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceUserMealWithWeight {
    UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int dayId, String menuName);

    boolean delete(int id, int dayId);

    UserMealWithWeight get(int id, int dayId);

    List<UserMealWithWeight> getAll(int dayId, String menuName);
}
