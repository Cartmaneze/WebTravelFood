package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.Journey;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.komarov.travelFood.JourneyTestData.MATCHER;
import static com.komarov.travelFood.JourneyTestData.*;
import static com.komarov.travelFood.UserTestData.*;

/**
 * Created by Никита on 08.11.2017.
 */
public class JourneyServiceTest extends AbstractServiceTest {

    @Autowired
    ServiceJourney service;

    @Test
    public void getAllJourneysTest() throws Exception {
        List list = service.getAll(USER_1_ID);
        MATCHER.assertListEquals(Arrays.asList(JOURNEY_1, JOURNEY_2, JOURNEY_3), list);
    }

    @Test
    public void journeyDeleteTest() throws Exception {
        service.delete(JOURNEY_1_ID, USER_1_ID);
        MATCHER.assertIsNull(service.get(JOURNEY_1_ID, USER_1_ID));
    }

    @Test
    public void journeySaveTest() throws Exception {
        Journey newJourney = new Journey("first", USER_2);
        service.save(newJourney, USER_2_ID);
    }
}
