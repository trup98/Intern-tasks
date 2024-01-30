package com.springmvc.springbootjpa.service.impl;

import com.springmvc.springbootjpa.model.CarEntity;
import com.springmvc.springbootjpa.repositry.CarRepository;
import com.springmvc.springbootjpa.request.dto.CarRequestDTO;
import com.springmvc.springbootjpa.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public String saveCar(CarRequestDTO carRequestDTO) {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarName(carRequestDTO.getCarName());
        this.carRepository.save(carEntity);
        return "Added";
    }

    @Override
    public List<CarEntity> search() {
        return this.carRepository.findAll();

    }

    @Override
    public Optional<CarEntity> findId(Long findId) {
        return this.carRepository.findById(findId);
//      When we don't use optional class as a return type we can use entity class and then we need to through Exception
//      .orElseThrow(()-> new RuntimeException("Not Found"+findId));
    }

    @Override
    public String delete(Long findId) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(findId);
        this.carRepository.delete(carEntity);
        return "Deleted";
    }

    @Override
    public String update(Long id, CarRequestDTO carRequestDTO) {
        // method 1
        CarEntity carEntity = this.carRepository.findById(id).orElseThrow(() -> new RuntimeException("error"));
        carEntity.setCarName(carRequestDTO.getCarName());
        this.carRepository.save(carEntity);

        return "Updated";
        // method 2

//        Optional<CarEntity> carEntity = this.carRepository.findById(id);
//        if (carEntity.isPresent()) {
//            carEntity.get().setCarName(carRequestDTO.getCarName());
//            this.carRepository.save(carEntity.get());
//            return "Updated";
//        } else {
//            return null;
//        }

    }
}
