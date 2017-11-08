package com.komarov.travelFood;

import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.autorizedUser.Day;

import java.util.Objects;

import static com.komarov.travelFood.JourneyTestData.JOURNEY_1;
/**
 * Created by Никита on 08.11.2017.
 */
public class DayTestData {
    public static final int DAY_1_ID = 100023;

    public static final Day DAY_1 = new Day(DAY_1_ID, "1", JOURNEY_1);

    public static final BeanMatcher<Day> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getJourney().getId(), actual.getJourney().getId())
                    )
    );
}
