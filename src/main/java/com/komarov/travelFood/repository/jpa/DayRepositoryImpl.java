package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.Day;
import com.komarov.travelFood.model.autorizedUser.Journey;
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
    public Day save(Day day, int journeyId) {
        if (!day.isNew() && get(day.getId(), journeyId) == null) {
            return null;
        }
        day.setJourney(em.getReference(Journey.class, journeyId));
        if (day.isNew()) {
            em.persist(day);
            return day;
        } else {
            return em.merge(day);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int journeyId) {
        return em.createNamedQuery(Day.DELETE)
                .setParameter("id", id)
                .setParameter("journeyId", journeyId)
                .executeUpdate() != 0;
    }

    @Override
    public Day get(int id, int journeyId) {
        Day day = em.find(Day.class, id);
        return day != null && day.getJourney().getId() == journeyId ? day : null;
    }

    @Override
    public List<Day> getAll(int journeyId) {
        return em.createNamedQuery(Day.GET_ALL, Day.class)
                .setParameter("journeyId", journeyId)
                .getResultList();
    }
}
