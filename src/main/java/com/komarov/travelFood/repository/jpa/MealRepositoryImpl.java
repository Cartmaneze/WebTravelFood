package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.repository.MealRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Никита on 14.05.2017.
 */

@Repository
@Transactional(readOnly = true)
public class MealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

//    @Override
//    @Transactional
//    public Meal saveOrUpdate(Meal meal) {
//        if (meal.isNew()) {
//            em.persist(meal);
//            return meal;
//        } else if (!meal.isNew() && get(meal.getId()) != null) {
//            return em.merge(meal);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    @Transactional
//    public boolean delete(int id) {
//        if (get(id) == null) {
//            return false;
//        } else {
//            return em.createNamedQuery(Meal.DELETE).setParameter("id", id).executeUpdate() != 0;
//        }
//    }
//
//    @Override
//    public Meal get(int id) {
//        Meal meal = em.find(Meal.class, id);
//        return meal;
//    }

    @Override
    public List<Meal> getAll() {
        return em.createNamedQuery(Meal.GET_ALL, Meal.class).getResultList();
    }
}
