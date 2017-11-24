package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.model.autorizedUser.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.komarov.travelFood.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected ServiceUser service;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setLogin("sdf");
        user.setPassword("sdfs");
        service.save(user);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_1_ID);
        MATCHER.assertEquals(user, USER_1);
    }

    @Test
    public void testGetByLoginPassword() throws Exception {
        User user = service.getByLoginPassword(USER_1.getLogin(), USER_1.getPassword());
        MATCHER.assertEquals(user, USER_1);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User user = service.getByLogin(USER_1.getLogin());
        MATCHER.assertEquals(user, USER_1);
    }

    @Test
    public void testGetAllJourneys() throws Exception {
        User user = service.get(100000);
        for (Journey journey : user.getJourneyList()) {
            System.out.println(journey.getName());
        }
    }
}
