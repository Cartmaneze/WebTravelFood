package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Никита on 11.10.2017.
 */
@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User meal) {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }
}
