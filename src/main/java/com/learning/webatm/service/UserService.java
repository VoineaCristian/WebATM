package com.learning.webatm.service;

import com.learning.webatm.model.User;

public interface UserService {

    User findUserByUsername(String username);
    User findUserById(Long id);

}
