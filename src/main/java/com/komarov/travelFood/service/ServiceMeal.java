package com.komarov.travelFood.service;

import com.komarov.travelFood.model.Meal;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceMeal {
    public void saveOrUpdate(Meal meal);

    public void delete(int id);

    public Meal get(int id);

    public List<Meal> getAll();
}
