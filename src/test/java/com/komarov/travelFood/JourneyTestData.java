package com.komarov.travelFood;

import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.autorizedUser.Journey;

import java.util.Objects;

import static com.komarov.travelFood.UserTestData.USER_1;
/**
 * Created by Никита on 08.11.2017.
 */
public class JourneyTestData {
    public static final int JOURNEY_1_ID = 100019;
    public static final int JOURNEY_2_ID = 100020;
    public static final int JOURNEY_3_ID = 100021;

    public static final Journey JOURNEY_1 = new Journey(JOURNEY_1_ID, "journey1", USER_1);
    public static final Journey JOURNEY_2 = new Journey(JOURNEY_2_ID, "journey2", USER_1);
    public static final Journey JOURNEY_3 = new Journey(JOURNEY_3_ID, "journey3", USER_1);

    public static final BeanMatcher<Journey> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getUser().getId(), actual.getUser().getId())
                    )
    );
}
