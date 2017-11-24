package com.komarov.travelFood.web.autorizedUserMeals.abstractControllers;

import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.service.ServiceJourney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Никита on 22.11.2017.
 */
public abstract class AbstractJourneyController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceJourney service;

    public Journey save(Journey meal, int userId){
        log.info("saveJourney");
        return service.save(meal, userId);
    }

    public boolean delete(int id, int userId){
        log.info("deleteJourney");
        return service.delete(id, userId);
    }

    public Journey get(int id, int userId){
        log.info("getJourney");
        return service.get(id, userId);
    }

    public List<Journey> getAll(int userId){
        log.info("getAllJourney");
        return service.getAll(userId);
    }
}
