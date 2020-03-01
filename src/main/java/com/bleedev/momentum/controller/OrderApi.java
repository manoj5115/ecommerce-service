package com.bleedev.momentum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bleedev.momentum.pojo.PurchaseInfo;
import com.bleedev.momentum.service.OrderService;

@RestController
@RequestMapping("/orderApi/v1")
public class OrderApi {
 
    private final OrderService orderService;
 
    public OrderApi(final OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/purchase")
    public ResponseEntity<String> validatePurchase(@RequestBody PurchaseInfo purchaseData) {
        return orderService.validatePurchase(purchaseData);
    }
}