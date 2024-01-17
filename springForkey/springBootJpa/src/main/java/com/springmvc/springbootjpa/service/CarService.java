package com.springmvc.springbootjpa.service;

import com.springmvc.springbootjpa.model.CarEntity;
import com.springmvc.springbootjpa.request.dto.CarRequestDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {
    String saveCar(CarRequestDTO carRequestDTO);

    List<CarEntity> search();

    Optional<CarEntity> findId(Long findId);

    String delete(Long findId);

    String update(Long id, CarRequestDTO carRequestDTO);
}
