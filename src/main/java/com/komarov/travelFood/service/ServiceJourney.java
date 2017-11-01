package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.Journey;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceJourney {
    Journey save(Journey meal, int userId);

    boolean delete(int id, int userId);

    Journey get(int id, int userId);

    List<Journey> getAll(int userId);
}
