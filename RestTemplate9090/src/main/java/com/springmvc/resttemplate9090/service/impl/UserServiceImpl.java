package com.springmvc.resttemplate9090.service.impl;

import com.springmvc.resttemplate9090.entity.UserEntity;
import com.springmvc.resttemplate9090.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;

    @Value("${common.url}")
    private String general;

    @Override
    public String findAllUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(general + "/user/findAllUser", HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String findById(Long findById) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(general + "/user/findById/" + findById, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String addNewUser(UserEntity userEntity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserEntity> httpEntity = new HttpEntity<>(userEntity, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(general + "/user/addUser", HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String updateUserById(Long userId, UserEntity userEntity) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserEntity> httpEntity = new HttpEntity<>(userEntity, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(general + "/user/updateUser/" + userId, HttpMethod.PUT, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String deleteUserById(Long deleteId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(general + "/user/deleteUser/" + deleteId, HttpMethod.DELETE, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
