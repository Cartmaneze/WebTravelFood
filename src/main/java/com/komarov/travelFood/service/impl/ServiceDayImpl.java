package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.autorizedUser.Day;
import com.komarov.travelFood.repository.DayRepository;
import com.komarov.travelFood.service.ServiceDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
@Service
public class ServiceDayImpl implements ServiceDay {

    @Autowired
    DayRepository dayRepository;

    public ServiceDayImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public Day save(Day meal, int journeyId) {
        return dayRepository.save(meal, journeyId);
    }

    @Override
    public boolean delete(int id, int journeyId) {
        return dayRepository.delete(id, journeyId);
    }

    @Override
    public Day get(int id, int journeyId) {
        return dayRepository.get(id, journeyId);
    }

    @Override
    public List<Day> getAll(int journeyId) {
        return dayRepository.getAll(journeyId);
    }
}
