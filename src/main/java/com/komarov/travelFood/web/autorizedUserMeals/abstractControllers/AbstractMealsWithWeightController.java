package com.komarov.travelFood.web.autorizedUserMeals.abstractControllers;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;
import com.komarov.travelFood.service.ServiceUserMealWithWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Никита on 22.11.2017.
 */
public class AbstractMealsWithWeightController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceUserMealWithWeight service;

    public UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int dayId, String menuName){
        log.info("saveOrUpdateMealWithWeight");
        return service.saveOrUpdate(meal, dayId, menuName);
    }

    public boolean delete(int id, int dayId){
        log.info("deleteMealWithWeight");
        return service.delete(id, dayId);
    }

    public UserMealWithWeight get(int id, int dayId){
        log.info("getMealWithWeight");
        return service.get(id, dayId);
    }

    public List<UserMealWithWeight> getAll(int dayId, String menuName){
        log.info("getAllMealWithWeight");
        return service.getAll(dayId, menuName);
    }
}
