package com.springboot.projection_springdatajpa.service;


import com.springboot.projection_springdatajpa.dto.ProductProjectionDTO;
import com.springboot.projection_springdatajpa.dto.ProductResponseDto;
import com.springboot.projection_springdatajpa.dto.RegisterRequestDto;

import java.util.List;

public interface ProductService  {
    void addProduct(RegisterRequestDto requestDto);

    List<ProductResponseDto> getAllProduct();

    ProductResponseDto getProductById(Long id);

    void updateProduct(Long id, RegisterRequestDto registerRequestDto);

    void deleteProduct(Long id);

    List<ProductProjectionDTO> projection();
}
