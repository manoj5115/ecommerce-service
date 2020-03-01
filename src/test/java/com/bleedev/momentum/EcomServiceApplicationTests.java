package com.bleedev.momentum;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bleedev.momentum.controller.CustomerApi;
import com.bleedev.momentum.controller.OrderApi;
import com.bleedev.momentum.controller.ProductApi;

@SpringBootTest
class EcomServiceApplicationTests {

	@Autowired
	private CustomerApi customerApi;
	
	@Autowired
	private ProductApi productApi;
	
	@Autowired
	private OrderApi orderrApi;

	@Test
	public void contexLoads() throws Exception {
		assertThat(customerApi).isNotNull();
		assertThat(productApi).isNotNull();
		assertThat(orderrApi).isNotNull();
	}
}
