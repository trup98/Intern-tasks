package com.springmvc.springbootjpa.repositry;

import com.springmvc.springbootjpa.model.CarEntity;
import com.springmvc.springbootjpa.request.dto.CarRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {


}
