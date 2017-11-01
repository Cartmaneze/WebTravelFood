package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;
import com.komarov.travelFood.repository.UserMealWithWeightRepository;
import com.komarov.travelFood.service.ServiceUserMealWithWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Никита on 01.11.2017.
 */
@Service
public class ServiceUserMealWithWeightImpl implements ServiceUserMealWithWeight {

    @Autowired
    UserMealWithWeightRepository userMealWithWeightRepository;

    public ServiceUserMealWithWeightImpl(UserMealWithWeightRepository userMealWithWeightRepository) {
        this.userMealWithWeightRepository = userMealWithWeightRepository;
    }

    @Override
    public UserMealWithWeight saveOrUpdate(UserMealWithWeight meal, int dayId, String menuName) {
        return userMealWithWeightRepository.saveOrUpdate(meal, dayId, menuName);
    }

    @Override
    public boolean delete(int id, int dayId) {
        return userMealWithWeightRepository.delete(id, dayId);
    }

    @Override
    public UserMealWithWeight get(int id, int dayId) {
        return userMealWithWeightRepository.get(id, dayId);
    }

    @Override
    public List<UserMealWithWeight> getAll(int dayId, String menuName) {
        return userMealWithWeightRepository.getAll(dayId, menuName);
    }
}
