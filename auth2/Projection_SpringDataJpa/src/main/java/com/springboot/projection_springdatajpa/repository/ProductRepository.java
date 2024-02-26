package com.springboot.projection_springdatajpa.repository;

import com.springboot.projection_springdatajpa.entity.ProductEntity;
import com.springboot.projection_springdatajpa.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT id, product_name, product_price from product_table", nativeQuery = true)
    List<ProductProjection> getRequiredAttributes();
}
