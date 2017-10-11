package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.Meal;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface UserMealRepository {

    Meal saveOrUpdate(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);
}
