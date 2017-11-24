package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.Meal;

import java.util.List;

/**
 * Created by Никита on 14.05.2017.
 */
public interface MealRepository {

//    Meal saveOrUpdate(Meal meal);
//
//    boolean delete(int id);
//
//    Meal get(int id);

    List<Meal> getAll();
}
