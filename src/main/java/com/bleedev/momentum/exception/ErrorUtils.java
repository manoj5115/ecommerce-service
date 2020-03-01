package com.bleedev.momentum.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;

import com.bleedev.momentum.exception.pojo.ApiSubError;
import com.bleedev.momentum.exception.pojo.ApiValidationError;

public class ErrorUtils {

	public static List<ApiSubError> getAllValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		return constraintViolations.stream().map(ErrorUtils::getSingleValidationError).collect(Collectors.toList());
	}

	private static ApiSubError getSingleValidationError(ConstraintViolation<?> cv) {
		return getSingleValidationError(cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
	}

	private static ApiSubError getSingleValidationError(String object, String field, Object rejectedValue, String message) {
		ApiSubError subError = new ApiValidationError(object, field, rejectedValue, message);
		return subError;
	}

}
