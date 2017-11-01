package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.repository.MealRepository;
import com.komarov.travelFood.service.ServiceMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Никита on 20.05.2017.
 */
@Service
public class ServiceMealImpl implements ServiceMeal {

    @Autowired
    MealRepository mealRepository;

    public ServiceMealImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public void saveOrUpdate(Meal meal) {
        if (mealRepository.saveOrUpdate(meal) == null) {
            System.out.println("not saved");
        } else {
            System.out.println("saved");
        }
    }

    @Override
    public void delete(int id) {
        if (mealRepository.delete(id)) {
            System.out.println("delete");
        } else {
            System.out.println("not delete");
        }
    }

    @Override
    public Meal get(int id) {
        return mealRepository.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return mealRepository.getAll();
    }
}
