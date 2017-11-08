package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.Day;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.komarov.travelFood.DayTestData.MATCHER;
import static com.komarov.travelFood.DayTestData.*;
import static com.komarov.travelFood.JourneyTestData.*;
/**
 * Created by Никита on 08.11.2017.
 */
public class DayServiceTest extends AbstractServiceTest {

    @Autowired
    ServiceDay service;

    @Test
    public void dayGetAllTest() throws Exception {
        List list = service.getAll(JOURNEY_1_ID);
        MATCHER.assertListEquals(Arrays.asList(DAY_1), list);
    }

    @Test
    public void dayDeleteTest() throws Exception {
        service.delete(DAY_1_ID, JOURNEY_1_ID);
        MATCHER.assertIsNull(service.get(DAY_1_ID, JOURNEY_1_ID));
    }

    @Test
    public void daySaveTest() throws Exception {
        Day newDay = new Day("secondDay", JOURNEY_2);
        service.save(newDay, JOURNEY_2_ID);
    }
}
