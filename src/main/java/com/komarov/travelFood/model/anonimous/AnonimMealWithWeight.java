package com.komarov.travelFood.model.anonimous;

import com.komarov.travelFood.model.Meal;

/**
 * Created by Никита on 06.09.2017.
 */
public class AnonimMealWithWeight {
    Meal meal;
    int weight;

    public AnonimMealWithWeight(Meal meal, int weight) {
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
