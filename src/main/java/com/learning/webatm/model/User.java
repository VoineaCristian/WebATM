package com.learning.webatm.model;


import com.learning.webatm.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name="phone_number", unique = true)
    private String phoneNumber;

    public User() {
    }

    public User(Long id, String username, UserRole role, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin(){
        return this.getRole() == UserRole.ROLE_ADMIN;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", accounts=" +
                '}';
    }
}
