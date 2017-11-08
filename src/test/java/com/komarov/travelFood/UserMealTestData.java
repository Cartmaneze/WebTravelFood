package com.komarov.travelFood;

import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.autorizedUser.UserMeal;

import java.util.Objects;

import static com.komarov.travelFood.UserTestData.USER_1;

/**
 * Created by Никита on 06.11.2017.
 */
public class UserMealTestData {
    public static final int USER_MEAL_1_ID = 100022;

    public static final UserMeal USER_MEAL_1 = new UserMeal(USER_MEAL_1_ID, "барбарис", 100500, USER_1);

    public static final BeanMatcher<UserMeal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getUser().getId(), actual.getUser().getId())
                    )
    );
}
