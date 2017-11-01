package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.autorizedUser.UserMeal;
import com.komarov.travelFood.repository.UserMealRepository;
import com.komarov.travelFood.service.ServiceUserMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
@Service
public class ServiceUserMealImpl implements ServiceUserMeal {

    @Autowired
    UserMealRepository userMealRepository;

    public ServiceUserMealImpl(UserMealRepository userMealRepository) {
        this.userMealRepository = userMealRepository;
    }

    @Override
    public UserMeal saveOrUpdate(UserMeal meal, int userId) {
        return userMealRepository.saveOrUpdate(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return userMealRepository.delete(id, userId);
    }

    @Override
    public UserMeal get(int id, int userId) {
        return userMealRepository.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return userMealRepository.getAll(userId);
    }
}
