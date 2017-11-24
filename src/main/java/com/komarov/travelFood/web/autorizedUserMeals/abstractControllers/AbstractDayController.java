package com.komarov.travelFood.web.autorizedUserMeals.abstractControllers;

import com.komarov.travelFood.model.autorizedUser.Day;
import com.komarov.travelFood.service.ServiceDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Никита on 22.11.2017.
 */
public abstract class AbstractDayController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ServiceDay service;

    public Day save(Day day, int journeyId){
        log.info("saveDay");
        return service.save(day, journeyId);
    }

    public boolean delete(int id, int journeyId){
        log.info("deleteDay");
        return service.delete(id, journeyId);
    }

    public Day get(int id, int journeyId){
        log.info("getDay");
        return service.get(id, journeyId);
    }

    public List<Day> getAll(int journeyId){
        log.info("getAllDay");
        return service.getAll(journeyId);
    }
}
