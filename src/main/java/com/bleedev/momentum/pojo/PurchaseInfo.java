package com.bleedev.momentum.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bleedev.momentum.entity.Customer;
import com.bleedev.momentum.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseInfo {

	@NotNull(message = "Customer id cannot be blank")
	private Long custId;
	
	private transient Customer customer;
	
	@Size(min=1, message = "Please select at least one product to purchase")
	private  List<Long> productCodes;
	
	private transient List<Product> products;
}
