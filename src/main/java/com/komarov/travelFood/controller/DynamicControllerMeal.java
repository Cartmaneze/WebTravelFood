package com.komarov.travelFood.controller;

import com.komarov.travelFood.model.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 13.09.2017.
 */
public class DynamicControllerMeal {
    private static List<Meal> dinamicMealList = new ArrayList<>();

    public static void addList(List<Meal> meals) {
        dinamicMealList = meals;
    }

    public static List<Meal> getDinamicMealList() {
        return dinamicMealList;
    }
}
