package com.bleedev.momentum.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bleedev.momentum.pojo.PurchaseInfo;

class UtilsTest {

	@Test
	void validatePurchaseInfoTest1() {
		PurchaseInfo info = new PurchaseInfo();
		info.setCustId(7L);
		List<Long> codes = new ArrayList<>();
		codes.add(101L);
		codes.add(201L);
		info.setProductCodes(codes);

		Utils.validatePurchaseInfo(info);
	}

	@Test
	void validatePurchaseInfoTest2() {
		PurchaseInfo info = new PurchaseInfo();

		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			Utils.validatePurchaseInfo(info);
		});

	}

}
