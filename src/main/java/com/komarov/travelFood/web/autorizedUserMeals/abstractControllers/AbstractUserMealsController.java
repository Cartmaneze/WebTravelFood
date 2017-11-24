package com.komarov.travelFood.web.autorizedUserMeals.abstractControllers;

import com.komarov.travelFood.model.autorizedUser.UserMeal;
import com.komarov.travelFood.service.ServiceUserMeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Никита on 22.11.2017.
 */
public abstract class AbstractUserMealsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceUserMeal service;

    public UserMeal saveOrUpdate(UserMeal meal, int userId) {
        log.info("saveOrUpdateUserMeal");
        return service.saveOrUpdate(meal, userId);
    }

    public boolean delete(int id, int userId){
        log.info("deleteUserMeal");
        return service.delete(id, userId);
    }

    public UserMeal get(int id, int userId){
        log.info("getUserMeal");
        return service.get(id, userId);
    }

    public List<UserMeal> getAll(int userId){
        log.info("getAllUserMeal");
        return service.getAll(userId);
    }
}
