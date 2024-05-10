package com.fullstacksecurity.backend.repository;

import com.fullstacksecurity.backend.entity.RoleEntity;
import com.fullstacksecurity.backend.entity.UserEntity;
import com.fullstacksecurity.backend.entity.UserRoleMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMappingEntity, Long> {
  Optional<UserRoleMappingEntity> findByUserId(UserEntity userEntity);

}
