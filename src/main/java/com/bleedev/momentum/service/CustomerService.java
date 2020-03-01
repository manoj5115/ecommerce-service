package com.bleedev.momentum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bleedev.momentum.entity.Customer;
import com.bleedev.momentum.repo.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(final CustomerRepository customerRepository, final ProductService productService) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Long custId) {
		return customerRepository.findById(custId);
	}

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

}