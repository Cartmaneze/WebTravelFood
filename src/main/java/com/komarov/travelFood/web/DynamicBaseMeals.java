package com.komarov.travelFood.web;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.service.ServiceMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Никита on 22.11.2017.
 */
@Controller
public class DynamicBaseMeals {

    @Autowired
    ServiceMeal service;

    public List<Meal> getAll() {
        return service.getAll();
    }
}
