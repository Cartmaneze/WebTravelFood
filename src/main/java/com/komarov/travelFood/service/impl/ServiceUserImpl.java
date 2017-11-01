package com.komarov.travelFood.service.impl;

import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.repository.UserRepository;
import com.komarov.travelFood.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Никита on 01.11.2017.
 */
@Service
public class ServiceUserImpl implements ServiceUser {

    @Autowired
    UserRepository userRepository;

    public ServiceUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User meal) {
        return userRepository.save(meal);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }
}
