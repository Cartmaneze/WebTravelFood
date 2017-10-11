package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.Journey;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface JourneyRepository {
    Journey save(Journey meal, int userId);

    boolean delete(int id, int userId);

    Journey get(int id, int userId);

    List<Journey> getAll(int userId);
}
