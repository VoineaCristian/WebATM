package com.learning.webatm.repository;

import com.learning.webatm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {


   User findUserByUsername(String username);

   User findUserById(Long id);

}
