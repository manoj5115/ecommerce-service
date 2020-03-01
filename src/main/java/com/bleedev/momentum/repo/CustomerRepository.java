package com.bleedev.momentum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.momentum.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
