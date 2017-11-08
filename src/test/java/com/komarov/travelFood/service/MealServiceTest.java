package com.komarov.travelFood.service;

import com.komarov.travelFood.model.Meal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.komarov.travelFood.MealTestData.MATCHER;
import static com.komarov.travelFood.MealTestData.MEAL_1;
import static com.komarov.travelFood.MealTestData.MEAL_1_ID;
/**
 * Created by Никита on 03.11.2017.
 */

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected ServiceMeal service;

    @Test
    public void testGetAll() throws Exception {
        for (Meal meal : service.getAll()) {
            System.out.println(meal.getName() + "   " + meal.getCalories());
        }
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_1_ID);
        MATCHER.assertEquals(MEAL_1, meal);
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal();
        newMeal.setName("барбарис");
        newMeal.setCalories(100500);
        service.saveOrUpdate(newMeal);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = service.get(100002);
        meal.setCalories(100500);
        service.saveOrUpdate(meal);
    }
}
