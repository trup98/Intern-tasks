package com.fullstacksecurity.backend.repository;

import com.fullstacksecurity.backend.entity.UserDetailsEntity;
import com.fullstacksecurity.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {


  Optional<UserDetailsEntity> findByUserId(UserEntity userEntity);


}
