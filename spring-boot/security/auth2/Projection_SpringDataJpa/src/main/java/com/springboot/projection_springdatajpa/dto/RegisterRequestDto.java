package com.springboot.projection_springdatajpa.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String productName;
    private String productDetails;
    private Double price;
    private String description;
}
