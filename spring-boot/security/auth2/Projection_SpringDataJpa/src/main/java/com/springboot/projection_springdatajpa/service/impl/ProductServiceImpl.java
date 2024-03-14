package com.springboot.projection_springdatajpa.service.impl;

import com.springboot.projection_springdatajpa.dto.ProductProjectionDTO;
import com.springboot.projection_springdatajpa.dto.ProductResponseDto;
import com.springboot.projection_springdatajpa.dto.RegisterRequestDto;
import com.springboot.projection_springdatajpa.entity.ProductEntity;
import com.springboot.projection_springdatajpa.exception.CustomException;
import com.springboot.projection_springdatajpa.projection.ProductProjection;
import com.springboot.projection_springdatajpa.repository.ProductRepository;
import com.springboot.projection_springdatajpa.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    static final String ERRORMSG = "Product Not Found";

    public ProductResponseDto mapToProductResponseDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductResponseDto.class);
    }

    @Override
    public void addProduct(RegisterRequestDto requestDto) {
        try {
            ProductEntity productEntity = new ProductEntity();

            productEntity.setProductName(requestDto.getProductName());
            productEntity.setProductDetails(requestDto.getProductDetails());
            productEntity.setDescription(requestDto.getDescription());
            productEntity.setPrice(requestDto.getPrice());

            productRepository.save(productEntity);

        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        try {
            return this.productRepository.findAll().stream().
                    map(this::mapToProductResponseDto).toList();
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }

    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        try {
            var product = this.productRepository.findById(id);
            if (product.isEmpty()) {
                throw new CustomException(ERRORMSG, HttpStatus.NOT_FOUND);

            }
            return this.modelMapper.map(product.get(), ProductResponseDto.class);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void updateProduct(Long id, RegisterRequestDto registerRequestDto) {
        try {
            var product = productRepository.findById(id);
            if (product.isEmpty()) {
                throw new CustomException(ERRORMSG, HttpStatus.NOT_FOUND);
            }
            ProductEntity productEntity = product.get();
            productEntity.setProductName(registerRequestDto.getProductName());
            productEntity.setPrice(registerRequestDto.getPrice());
            productEntity.setDescription(registerRequestDto.getDescription());
            productEntity.setProductDetails(registerRequestDto.getProductDetails());
            this.productRepository.save(productEntity);

        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            var product = productRepository.findById(id);
            if (product.isEmpty()) {
                throw new CustomException(ERRORMSG, HttpStatus.NOT_FOUND);
            }
            this.productRepository.deleteById(id);
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        }
    }

    @Override
    public List<ProductProjectionDTO> projection() {
        List<ProductProjection> productProjections = this.productRepository.getRequiredAttributes();
        if (productProjections.isEmpty()) {
            throw new CustomException(ERRORMSG, HttpStatus.NOT_FOUND);
        }
        List<ProductProjectionDTO> projectionDTOS = new ArrayList<>();
        for (ProductProjection productProjection : productProjections) {
            projectionDTOS.add(new ProductProjectionDTO(
                    productProjection.getId(),
                    productProjection.getProductName(),
                    productProjection.getProductPrice()
            ));
        }
        return projectionDTOS;
    }
}
