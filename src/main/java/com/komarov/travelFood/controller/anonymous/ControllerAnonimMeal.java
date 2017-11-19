package com.komarov.travelFood.controller.anonymous;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.service.impl.ServiceMealImpl;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControllerAnonimMeal {

    ServiceMealImpl serviceMeal;

    public ControllerAnonimMeal(ServiceMealImpl serviceMeal) {
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
