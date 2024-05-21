package com.fullstacksecurity.backend.repository;

import com.fullstacksecurity.backend.entity.RoleEntity;
import com.fullstacksecurity.backend.response.dto.RoleResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


  @Query("select r from RoleEntity r where r.isDelete = false ")
  List<RoleEntity> findAllActiveRole();

  Optional<RoleEntity> findByRole(String roleName);
}
