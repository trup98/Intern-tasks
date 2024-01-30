package com.springmvc.springbootjpa.controller;

import com.springmvc.springbootjpa.model.CarEntity;
import com.springmvc.springbootjpa.request.dto.CarRequestDTO;
import com.springmvc.springbootjpa.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

//  Add Data
    @PostMapping("/add")
    public String addCar(@RequestBody CarRequestDTO carRequestDTO) {
        return this.carService.saveCar(carRequestDTO);
    }
//  Find All Data
    @GetMapping("/search")
    public List<CarEntity> search(){
        return this.carService.search();
    }
//  Find Data By Id
    @GetMapping("/id/{findId}")
    public Optional<CarEntity> findID(@PathVariable Long findId){
        return this.carService.findId(findId);
    }
//  Delete Data By Id
    @DeleteMapping("/delete/id/{findId}")
    public String deleteById(@PathVariable Long findId){
        return this.carService.delete(findId);
    }
//  Update Data By Id
    @PutMapping("/update/id/{id}")
    public String updateById(@PathVariable Long id,@RequestBody CarRequestDTO carRequestDTO){
        return this.carService.update(id,carRequestDTO);
    }
}
