package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;
import com.komarov.travelFood.repository.UserMealWithWeightRepository;
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
public class UserMealWithWeightRepositoryImpl implements UserMealWithWeightRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int userId) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public UserMealWithWeight get(int id, int userId) {
        return null;
    }

    @Override
    public List<UserMealWithWeight> getAll(int userId) {
        return null;
    }
}
