package com.komarov.travelFood.service;

import com.komarov.travelFood.model.autorizedUser.Day;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
public interface ServiceDay {
    Day save(Day meal, int journeyId);

    boolean delete(int id, int journeyId);

    Day get(int id, int journeyId);

    List<Day> getAll(int journeyId);
}
