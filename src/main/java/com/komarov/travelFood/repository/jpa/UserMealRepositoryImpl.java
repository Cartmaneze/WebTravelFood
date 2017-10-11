package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.repository.UserMealRepository;
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
public class UserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal saveOrUpdate(Meal meal, int userId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }
}
