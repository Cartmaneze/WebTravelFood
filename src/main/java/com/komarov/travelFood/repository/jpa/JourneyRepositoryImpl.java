package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.model.autorizedUser.User;
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
    public Journey save(Journey journey, int userId) {
        if (!journey.isNew() && get(journey.getId(), userId) == null) {
            return null;
        }
        journey.setUser(em.getReference(User.class, userId));
        if (journey.isNew()) {
            em.persist(journey);
            return journey;
        } else {
            return em.merge(journey);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Journey.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Journey get(int id, int userId) {
        Journey journey = em.find(Journey.class, id);
        return journey != null && journey.getUser().getId() == userId ? journey : null;
    }

    @Override
    public List<Journey> getAll(int userId) {
        return em.createNamedQuery(Journey.GET_ALL, Journey.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
