package com.learning.webatm.model;

import com.learning.webatm.enums.UserRole;

import java.util.List;


public class User {

    private Long Id;
    private String username;
    private UserRole role;
    private List<Account> accounts;
    private String phoneNumber;

    public User() {
    }

    public User(Long id, String username, List<Account> accounts, UserRole role, String phoneNumber) {
        Id = id;
        this.username = username;
        this.accounts = accounts;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isAdmin(){
        return this.getRole() == UserRole.ROLE_ADMIN;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", accounts=" + accounts +
                '}';
    }
}
