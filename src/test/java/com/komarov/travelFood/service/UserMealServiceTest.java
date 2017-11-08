package com.komarov.travelFood.service;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.model.autorizedUser.UserMeal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.komarov.travelFood.UserMealTestData.*;
import static com.komarov.travelFood.UserTestData.USER_1_ID;
import static com.komarov.travelFood.UserTestData.USER_2_ID;
/**
 * Created by Никита on 06.11.2017.
 */
public class UserMealServiceTest extends AbstractServiceTest {

    @Autowired
    ServiceUserMeal service;
    @Autowired
    ServiceMeal serviceMeal;

    @Test
    public void testSaveAllDefaultMealsTest() throws Exception {
        List<Meal> meals = serviceMeal.getAll();
        for (Meal meal : meals) {
            UserMeal userMeal = new UserMeal();
            userMeal.setName(meal.getName());
            userMeal.setCalories(meal.getCalories());
            service.saveOrUpdate(userMeal, USER_1_ID);
        }
    }

    @Test
    public void testUserMealGetTest() throws Exception {
        UserMeal userMeal = service.get(USER_MEAL_1_ID, USER_1_ID);
        MATCHER.assertEquals(userMeal, USER_MEAL_1);
    }

    @Test (expected = AssertionError.class)
    public void testWrongUserMealGetTest() throws Exception {
        UserMeal userMeal = service.get(USER_MEAL_1_ID, USER_2_ID);
        MATCHER.assertEquals(userMeal, USER_MEAL_1);
    }

    @Test (expected = AssertionError.class)
    public void testUserWrongMealGetTest() throws Exception {
        UserMeal userMeal = service.get(100003, USER_1_ID);
        MATCHER.assertEquals(userMeal, USER_MEAL_1);
    }

    @Test (expected = NullPointerException.class)
    public void testUserChangeTest() throws Exception {
        UserMeal userMeal = service.get(USER_MEAL_1_ID, USER_1_ID);
        UserMeal changeUserMeal = service.saveOrUpdate(userMeal, USER_2_ID);
        changeUserMeal.getCalories();
    }

    @Test
    public void testUserMealDeleteTest() throws Exception {
        service.delete(USER_MEAL_1_ID, USER_1_ID);
        MATCHER.assertIsNull(service.get(USER_MEAL_1_ID, USER_1_ID));
    }

    @Test
    public void testUserMealGetAllTest() throws Exception {
        MATCHER.assertListEquals(Arrays.asList(USER_MEAL_1), service.getAll(USER_1_ID));
    }
}
