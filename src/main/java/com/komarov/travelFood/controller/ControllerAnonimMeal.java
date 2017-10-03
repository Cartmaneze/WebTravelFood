package com.komarov.travelFood.controller;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.service.ServiceMeal;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControllerAnonimMeal {

    ServiceMeal serviceMeal;

    public ControllerAnonimMeal(ServiceMeal serviceMeal) {
        this.serviceMeal = serviceMeal;
    }

    public Meal get(int id) {
        return serviceMeal.get(id);
    }

    public List<Meal> getAll() {
        return serviceMeal.getAll();
    }

    public void saveOrUpdate(Meal meal) {
        serviceMeal.saveOrUpdate(meal);
    }

    public void delete(int id) {
        serviceMeal.delete(id);
    }
}
