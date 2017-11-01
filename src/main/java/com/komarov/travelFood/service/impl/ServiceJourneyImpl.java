package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.repository.JourneyRepository;
import com.komarov.travelFood.service.ServiceJourney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
@Service
public class ServiceJourneyImpl implements ServiceJourney {

    @Autowired
    JourneyRepository journeyRepository;

    public ServiceJourneyImpl(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @Override
    public Journey save(Journey meal, int userId) {
        return journeyRepository.save(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return journeyRepository.delete(id, userId);
    }

    @Override
    public Journey get(int id, int userId) {
        return journeyRepository.get(id, userId);
    }

    @Override
    public List<Journey> getAll(int userId) {
        return journeyRepository.getAll(userId);
    }
}
