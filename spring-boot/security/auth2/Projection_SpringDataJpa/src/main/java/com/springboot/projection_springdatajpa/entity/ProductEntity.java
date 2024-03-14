package com.springboot.projection_springdatajpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_table")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "prduct_details")
    private String productDetails;
    @Column(name = "product_price")
    private Double price;
    @Column(name = "product_description")
    private String description;


}
