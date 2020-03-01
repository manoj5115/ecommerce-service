package com.bleedev.momentum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bleedev.momentum.entity.Customer;
import com.bleedev.momentum.entity.Product;
import com.bleedev.momentum.exception.NotEnoughPointsException;
import com.bleedev.momentum.exception.ResourceNotFoundException;
import com.bleedev.momentum.pojo.PurchaseInfo;
import com.bleedev.momentum.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	private final CustomerService customerService;
	
	private final ProductService productService;

	public OrderService(final CustomerService customerService, final ProductService productService) {
		this.customerService = customerService;
		this.productService = productService;
	}

	public ResponseEntity<String> validatePurchase(PurchaseInfo purchaseData) {
		Utils.validatePurchaseInfo(purchaseData);
		Optional<Customer> customer = customerService.getCustomerById(purchaseData.getCustId());
		if (customer.isEmpty()) {
			throw new ResourceNotFoundException("Customer id is not valid");
		}

		List<Product> products = productService.getAllProductsById(purchaseData.getProductCodes());
		if (purchaseData.getProductCodes().size() != products.size()) {
			throw new ResourceNotFoundException("One or more products are not valid.");
		}

		int adPoints = customer.get().getAdPoints();
		int productSum = products.stream().mapToInt(Product::getPointsCost).sum();

		if (adPoints < productSum) {
			String message = String.format("The customer with %d points does not have enough points to buy product(s) worth %d points.", adPoints, productSum);
			log.error(message);
			throw new NotEnoughPointsException(message);
		}
		String message = String.format("Purchase is valid. The customer with %d points have enough points to buy product(s) worth %d points.", adPoints, productSum);
		log.info(message);
		return ResponseEntity.ok(message);
	}

}