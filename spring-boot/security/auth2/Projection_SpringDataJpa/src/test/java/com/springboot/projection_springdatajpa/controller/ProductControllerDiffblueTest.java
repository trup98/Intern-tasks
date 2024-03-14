package com.springboot.projection_springdatajpa.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.projection_springdatajpa.dto.ProductResponseDto;
import com.springboot.projection_springdatajpa.dto.RegisterRequestDto;
import com.springboot.projection_springdatajpa.service.ProductService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#getAllProduct()}
     */
    @Test
    void testGetAllProduct() throws Exception {
        // Arrange
        when(productService.getAllProduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":200,\"message\":\"Product received SuccessFully\",\"data\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#getAllProduct()}
     */
    @Test
    void testGetAllProduct2() throws Exception {
        // Arrange
        when(productService.getAllProduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/all");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":200,\"message\":\"Product received SuccessFully\",\"data\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#getProductById(Long)}
     */
    @Test
    void testGetProductById() throws Exception {
        // Arrange
        when(productService.getProductById(Mockito.<Long>any())).thenReturn(new ProductResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"status\":200,\"message\":\"Product received SuccessFully\",\"data\":{\"id\":null,\"productName\":null,"
                                        + "\"productDetails\":null,\"price\":null}}"));
    }

    /**
     * Method under test:
     * {@link ProductController#updateProduct(Long, RegisterRequestDto)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        // Arrange
        doNothing().when(productService).updateProduct(Mockito.<Long>any(), Mockito.<RegisterRequestDto>any());

        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setDescription("The characteristics of someone or something");
        registerRequestDto.setPrice(10.0d);
        registerRequestDto.setProductDetails("Product Details");
        registerRequestDto.setProductName("Product Name");
        String content = (new ObjectMapper()).writeValueAsString(registerRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/product/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":200,\"message\":\"Product Update Successfully\",\"data\":{}}"));
    }

    /**
     * Method under test:
     * {@link ProductController#addNewProduct(RegisterRequestDto)}
     */
    @Test
    void testAddNewProduct() throws Exception {
        // Arrange
        doNothing().when(productService).addProduct(Mockito.<RegisterRequestDto>any());

        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setDescription("The characteristics of someone or something");
        registerRequestDto.setPrice(10.0d);
        registerRequestDto.setProductDetails("Product Details");
        registerRequestDto.setProductName("Product Name");
        String content = (new ObjectMapper()).writeValueAsString(registerRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"status\":200,\"message\":\"Product Added !\",\"data\":{}}"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/delete/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":200,\"message\":\"Product Delete Successfully\",\"data\":{}}"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/delete/{id}", 1L);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":200,\"message\":\"Product Delete Successfully\",\"data\":{}}"));
    }

    /**
     * Method under test: {@link ProductController#projectionApi()}
     */
    @Test
    void testProjectionApi() throws Exception {
        // Arrange
        when(productService.projection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/projection");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"status\":200,\"message\":\"Projection Match\",\"data\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#projectionApi()}
     */
    @Test
    void testProjectionApi2() throws Exception {
        // Arrange
        when(productService.projection()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/projection");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"status\":200,\"message\":\"Projection Match\",\"data\":[]}"));
    }
}
