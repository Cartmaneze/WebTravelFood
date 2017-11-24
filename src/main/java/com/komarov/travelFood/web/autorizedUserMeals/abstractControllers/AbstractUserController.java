package com.komarov.travelFood.web.autorizedUserMeals.abstractControllers;

import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Никита on 22.11.2017.
 */
public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceUser service;

    public User save(User user) {
        log.info("saveUser");
        return service.save(user);
    }

    public User get(int id) {
        log.info("getUser");
        return service.get(id);
    }

    public User getByLoginPassword(String login, String password) {
        log.info("getByLoginPasswordUser");
        return service.getByLoginPassword(login, password);
    }

    public User getByLogin(String login) {
        log.info("getByLoginUser");
        return service.getByLogin(login);
    }
}
