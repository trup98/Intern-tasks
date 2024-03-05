package com.springboot.exceltodb.repository;

import com.springboot.exceltodb.entity.ExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<ExcelEntity,Long> {
}
