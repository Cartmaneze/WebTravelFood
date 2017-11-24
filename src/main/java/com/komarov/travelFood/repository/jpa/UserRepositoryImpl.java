package com.komarov.travelFood.repository.jpa;

import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.repository.UserRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
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
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (!user.isNew() && get(user.getId()) == null) {
            return null;
        }
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByLoginPassword(String login, String password) {
        List<User> users = em.createNamedQuery(User.BY_LOGIN_PASSWORD, User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = em.createNamedQuery(User.BY_LOGIN, User.class)
                .setParameter("login", login)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
}
