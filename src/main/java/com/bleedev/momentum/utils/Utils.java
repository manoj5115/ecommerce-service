package com.bleedev.momentum.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.CollectionUtils;

import com.bleedev.momentum.pojo.PurchaseInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static void validatePurchaseInfo(PurchaseInfo purchaseData) {
		Set<ConstraintViolation<PurchaseInfo>> violations = getValidator().validate(purchaseData);
		if (!CollectionUtils.isEmpty(violations)) {
			throw new ConstraintViolationException(violations);
		} else {
			log.info("Purchase information is valid.");
		}
	}

	public static Validator getValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		return validator;
	}
}
