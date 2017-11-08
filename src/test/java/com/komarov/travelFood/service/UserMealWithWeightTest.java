package com.komarov.travelFood.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Никита on 06.11.2017.
 */
public class UserMealWithWeightTest extends AbstractServiceTest {

    @Autowired
    ServiceUserMealWithWeight service;

    @Test
    public void UserMealWithWeightGetAllTest() {
        List list = service.getAll(100023, "завтрак");
        System.out.println(list.size());
    }

    @Test
    public void UserMealWithWeightDeleteTest() {
        service.delete(100024, 100023);
    }
}
