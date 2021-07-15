package com.learning.webatm.service;

import com.learning.webatm.model.User;
import com.learning.webatm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }
}
