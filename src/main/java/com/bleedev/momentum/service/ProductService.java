package com.bleedev.momentum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bleedev.momentum.entity.Product;
import com.bleedev.momentum.repo.ProductRepository;

@Service
public class ProductService {
 
    private final ProductRepository productRepository;
    
    public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
    
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
	public List<Product> getAllProductsById(List<Long> prdCodes) {
        return productRepository.findAllById(prdCodes);
    }

	public Product addProduct(Product product) {
		return productRepository.save(product);		
	}
}