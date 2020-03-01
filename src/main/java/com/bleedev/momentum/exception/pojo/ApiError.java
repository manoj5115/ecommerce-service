package com.bleedev.momentum.exception.pojo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ApiError {

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private String message;

	private String debugMessage;
	
	private Throwable throwable;
	
	private List<ApiSubError> detailedErrors;
	
	
    
	public ApiError(HttpStatus status, LocalDateTime timestamp, String message, String debugMessage, Throwable throwable, List<ApiSubError> detailedErrors) {
		this.status = status;
		this.timestamp = timestamp == null ? LocalDateTime.now() : timestamp;
		this.message = message;
		this.debugMessage = throwable == null ? debugMessage : throwable.getMessage();
		this.throwable = throwable;
		this.detailedErrors = detailedErrors;
	}

}
