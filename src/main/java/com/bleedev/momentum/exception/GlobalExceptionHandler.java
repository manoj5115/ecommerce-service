package com.bleedev.momentum.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bleedev.momentum.exception.pojo.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
		ApiError apiError = ApiError.builder().status(BAD_REQUEST).message("Validation Error")
				.detailedErrors(ErrorUtils.getAllValidationErrors(ex.getConstraintViolations())).build();

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResorceNotFound(ResourceNotFoundException ex) {
		ApiError apiError = ApiError.builder().status(NOT_FOUND).message(ex.getMessage()).build();

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		ApiError apiError = ApiError.builder().status(INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();

		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}