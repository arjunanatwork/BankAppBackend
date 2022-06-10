package com.arjunanr.bankapp.expection;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arjunanr.bankapp.dto.ErrorApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class ErrorHandler {

	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorApiResponse> resolveNotFoundException(NotFoundException exception) {
		log.error("Some Business Exception Occured: {} ", exception.getMessage());
		ErrorApiResponse errApiResponse = ErrorApiResponse.builder()
	            .success(false)
	            .message(exception.getMessage())
	            .build();
		return new ResponseEntity<>(errApiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorApiResponse> resolveBusinessException(BusinessException exception) {
		log.error("BusinessException Exception Occured: {} ", exception.getMessage());
		ErrorApiResponse errApiResponse = ErrorApiResponse.builder()
	            .success(false)
	            .message(exception.getMessage())
	            .build();
		return new ResponseEntity<>(errApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ResponseEntity<ErrorApiResponse> handleThrowable(final Throwable exception) {
		log.error("Unexpected Error", exception);
		ErrorApiResponse errApiResponse = ErrorApiResponse.builder()
	            .success(false)
	            .message(exception.getMessage())
	            .build();
		return new ResponseEntity<>(errApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
