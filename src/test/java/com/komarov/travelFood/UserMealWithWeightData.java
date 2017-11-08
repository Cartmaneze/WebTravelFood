package com.komarov.travelFood;

import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;

import java.util.Objects;

import static com.komarov.travelFood.UserMealTestData.USER_MEAL_1;
import static com.komarov.travelFood.DayTestData.DAY_1;
/**
 * Created by Никита on 08.11.2017.
 */
public class UserMealWithWeightData {
    public static final int USER_MEAL_WITH_WEIGHT_1_ID = 100024;

    public static final UserMealWithWeight USER_MEAL_WITH_WEIGHT_1 = new UserMealWithWeight(USER_MEAL_WITH_WEIGHT_1_ID, 100, "завтрак", USER_MEAL_1, DAY_1);

    public static final BeanMatcher<UserMealWithWeight> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getWeight(), actual.getWeight())
                            && Objects.equals(expected.getMenuName(), actual.getMenuName())
                            && Objects.equals(expected.getMeal().getId(), actual.getMeal().getId())
                    )
    );
}
