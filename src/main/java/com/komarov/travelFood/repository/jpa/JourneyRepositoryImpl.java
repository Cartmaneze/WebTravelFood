package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.repository.JourneyRepository;
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
public class JourneyRepositoryImpl implements JourneyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Journey save(Journey meal, int userId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Journey get(int id, int userId) {
        return null;
    }

    @Override
    public List<Journey> getAll(int userId) {
        return null;
    }
}
