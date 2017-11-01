package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.Day;
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
    public UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int dayId, String menuName) {
        if (!meal.isNew() && get(meal.getId(), dayId) == null) {
            return null;
        }
        meal.setDay(em.getReference(Day.class, dayId));
        meal.setMenuName(menuName);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int dayId) {
        return em.createNamedQuery(UserMealWithWeight.DELETE)
                .setParameter("id", id)
                .setParameter("dayId", dayId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMealWithWeight get(int id, int dayId) {
        UserMealWithWeight meal = em.find(UserMealWithWeight.class, id);
        return meal != null && meal.getDay().getId() == dayId ? meal : null;
    }

    @Override
    public List<UserMealWithWeight> getAll(int dayId, String menuName) {
        return em.createNamedQuery(UserMealWithWeight.GET_ALL, UserMealWithWeight.class)
                .setParameter("dayId", dayId)
                .setParameter("menuName", menuName)
                .getResultList();
    }
}
