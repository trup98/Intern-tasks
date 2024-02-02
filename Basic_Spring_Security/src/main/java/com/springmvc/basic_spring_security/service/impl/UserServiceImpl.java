package com.springmvc.basic_spring_security.service.impl;

import com.springmvc.basic_spring_security.model.UserEntity;
import com.springmvc.basic_spring_security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    List<UserEntity> userEntities = new ArrayList<>();

    public void userServiceImpl() {
        userEntities.add(new UserEntity("modi", "456", "patel"));
        userEntities.add(new UserEntity("Trup", "123", "patel"));
        userEntities.add(new UserEntity("pratik", "987", "panchal"));
    }

    //    getAll User
    public List<UserEntity> getAllUser() {
        System.out.println("userEntities = " + userEntities);
        userServiceImpl();
        return this.userEntities;
    }

    //    getOneUser
    public UserEntity getById(String userName) {
        return this.userEntities.
                stream().
                filter(userEntity -> userEntity.getUserName().equals(userName))
                .findAny().
                orElse(null);
    }

    //    Add User
    public UserEntity addNewUser(UserEntity userEntity) {
        this.userEntities.add(userEntity);
        return userEntity;
    }
}
