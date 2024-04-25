package com.learning.fullstack.backend.repository;

import com.learning.fullstack.backend.entity.UserEntity;
import com.learning.fullstack.backend.entity.UserHobbyMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHobbyMappingRepository extends JpaRepository<UserHobbyMappingEntity, Long> {


  List<UserHobbyMappingEntity> findByUserId(UserEntity id);
}
