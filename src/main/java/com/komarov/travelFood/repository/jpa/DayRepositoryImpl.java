package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.Day;
import com.komarov.travelFood.repository.DayRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Никита on 11.10.2017.
 */
@Repository
@Transactional(readOnly = true)
public class DayRepositoryImpl implements DayRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Day save(Day meal, int journeyId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int journeyId) {
        return false;
    }

    @Override
    public Day get(int id, int journeyId) {
        return null;
    }

    @Override
    public List<Day> getAll(int journeyId) {
        return null;
    }
}
