package com.learning.webatm.repository;

import com.learning.webatm.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserRepo {

    private List<User> users;

    public UserRepo(){
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public User findUserByUsername(String username) {

        List<User> usersQuery = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());

        if(!usersQuery.isEmpty()){
            return usersQuery.get(0);
        } else return null;
    }

    public User findUserById(Long id) {
        List<User> usersQuery = users.stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        if(!usersQuery.isEmpty()){
            return users.get(0);
        } else return null;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }
}
