package com.springboot.projection_springdatajpa.controller;


import com.springboot.projection_springdatajpa.dto.ApiResponse;
import com.springboot.projection_springdatajpa.dto.RegisterRequestDto;
import com.springboot.projection_springdatajpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //    add product
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewProduct(@RequestBody RegisterRequestDto requestDto) {
        this.productService.addProduct(requestDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Added !", new HashMap<>()), HttpStatus.OK);
    }

    //    get all product
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProduct() {
        var response = this.productService.getAllProduct();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product received SuccessFully", response), HttpStatus.OK);
    }

    //    get product by id
    @GetMapping({"/{id}"})
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        var response = this.productService.getProductById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product received SuccessFully", response), HttpStatus.OK);
    }

    //    update product
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id, @RequestBody RegisterRequestDto registerRequestDto) {
        this.productService.updateProduct(id, registerRequestDto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Update Successfully", new HashMap<>()), HttpStatus.OK);
    }

    //    delete product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Product Delete Successfully", new HashMap<>()), HttpStatus.OK);
    }

    //    custom database query
    @GetMapping("/projection")
    public ResponseEntity<ApiResponse> projectionApi() {
        var projection = this.productService.projection();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Projection Match", projection), HttpStatus.OK);
    }
}
