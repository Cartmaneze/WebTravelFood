package com.komarov.travelFood.web.anonymousMeals;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.service.impl.ServiceMealImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControllerAnonimMeal {

    @Autowired
    ServiceMealImpl serviceMeal;

    public ControllerAnonimMeal(ServiceMealImpl serviceMeal) {
        this.serviceMeal = serviceMeal;
    }

    public ControllerAnonimMeal() {

    }

//    public Meal get(int id) {
//        return serviceMeal.get(id);
//    }

    public List<Meal> getAll() {
        return serviceMeal.getAll();
    }

//    public void saveOrUpdate(Meal meal) {
//        serviceMeal.saveOrUpdate(meal);
//    }
//
//    public void delete(int id) {
//        serviceMeal.delete(id);
//    }
}
