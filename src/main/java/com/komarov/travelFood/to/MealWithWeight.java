package com.komarov.travelFood.to;

import com.komarov.travelFood.model.Meal;

/**
 * Created by Никита on 06.09.2017.
 */
public class MealWithWeight {
    Meal meal;
    int weight;

    public MealWithWeight(Meal meal, int weight) {
        this.meal = meal;
        this.weight = weight;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getWeight() {
        return weight;
    }

}
