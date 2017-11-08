package com.komarov.travelFood;

/**
 * Created by Никита on 06.11.2017.
 */

import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.Meal;

import java.util.Objects;

import static com.komarov.travelFood.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, "гречка", 330);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, "кукурузная крупа", 337);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );
}
