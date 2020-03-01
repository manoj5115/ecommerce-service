package com.bleedev.momentum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bleedev.momentum.entity.Customer;
import com.bleedev.momentum.entity.Product;
import com.bleedev.momentum.pojo.PurchaseInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllProductsTest() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].code").isNotEmpty());
	}

	@Test
	public void addProductTest() throws Exception {
		Product product = new Product();
		product.setName("Test product");
		product.setPointsCost(10);
		this.mockMvc
				.perform(post("/productApi/v1/products").content(asJsonString(product))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());

	}

	@Test
	public void getAllCustomersTest() throws Exception {
		this.mockMvc.perform(get("/customerApi/v1/customers").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].id").isNotEmpty());
	}

	@Test
	public void addCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setName("Test Customer");
		customer.setAdPoints(100);

		this.mockMvc
				.perform(post("/customerApi/v1/customers").content(asJsonString(customer))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());

	}

	@Test
	public void orderPurchaseTest1() throws Exception {
		// Valid purchase use-case where valid customer has enough Active day points to purchase selected products.
		PurchaseInfo info = new PurchaseInfo();
		info.setCustId(7L);
		List<Long> codes = new ArrayList<>();
		codes.add(101L);
		codes.add(201L);
		info.setProductCodes(codes);

		this.mockMvc
				.perform(post("/orderApi/v1/purchase").content(asJsonString(info))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void orderPurchaseTest2() throws Exception {
		// Another purchase use-case where valid customer does not have enough Active day points to purchase selected products. API throws Business Exception in this scenario with 500 as HTTP response code.
		PurchaseInfo info = new PurchaseInfo();
		info.setCustId(1L);
		List<Long> codes = new ArrayList<>();
		codes.add(101L);
		codes.add(201L);
		info.setProductCodes(codes);

		this.mockMvc
				.perform(post("/orderApi/v1/purchase").content(asJsonString(info))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void orderPurchaseTest3() throws Exception {
		// Use-case where either Customer Id and/or at least one Product code is not valid.
		PurchaseInfo info = new PurchaseInfo();
		info.setCustId(1L);
		List<Long> codes = new ArrayList<>();
		info.setProductCodes(codes);

		this.mockMvc
				.perform(post("/orderApi/v1/purchase").content(asJsonString(info))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}
	
	private String asJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(object);
		return jsonString;

	}
}