package com.springboot.projection_springdatajpa.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ProductProjection {

    Long getId();
    @Value("#{target.product_name}")
    String getProductName();
    @Value("#{target.product_price}")
    Double getProductPrice();
}
