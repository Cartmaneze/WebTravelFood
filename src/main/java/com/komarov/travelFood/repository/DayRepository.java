package com.komarov.travelFood.repository;

import com.komarov.travelFood.model.autorizedUser.Day;

import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
public interface DayRepository {
    Day save(Day meal, int journeyId);

    boolean delete(int id, int journeyId);

    Day get(int id, int journeyId);

    List<Day> getAll(int journeyId);
}
