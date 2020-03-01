package com.bleedev.momentum.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bleedev.momentum.entity.Product;
import com.bleedev.momentum.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/productApi/v1")
@Slf4j
public class ProductApi {
 
    private final ProductService productService;
 
    public ProductApi(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
	
	@PostMapping("/products")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        log.info("A new Product was added successfully with this product code: {}", addedProduct.getCode());
        return ResponseEntity.status(CREATED).body(addedProduct);
    }
}