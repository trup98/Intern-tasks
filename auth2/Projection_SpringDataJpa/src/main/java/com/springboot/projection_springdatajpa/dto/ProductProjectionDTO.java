package com.springboot.projection_springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductProjectionDTO {

    private Long id;
    private String productName;
    private Double price;
}
