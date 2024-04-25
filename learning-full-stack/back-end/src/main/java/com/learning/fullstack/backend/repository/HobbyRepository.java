package com.learning.fullstack.backend.repository;

import com.learning.fullstack.backend.entity.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<HobbyEntity,Long> {
}
