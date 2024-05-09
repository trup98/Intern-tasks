package com.fullstacksecurity.backend.repository;

import com.fullstacksecurity.backend.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity,Long> {
}
