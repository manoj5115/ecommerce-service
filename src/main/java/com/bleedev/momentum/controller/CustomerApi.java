package com.bleedev.momentum.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bleedev.momentum.entity.Customer;
import com.bleedev.momentum.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customerApi/v1")
@Slf4j
public class CustomerApi {
 
    private final CustomerService customerService;
 
    public CustomerApi(final CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }
	
	@PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer addedCustomer = customerService.addCustomer(customer);
        log.info("A new customer was added successfully with this customer id: {}", addedCustomer.getId());
        return ResponseEntity.status(CREATED).body(addedCustomer);
    }
}