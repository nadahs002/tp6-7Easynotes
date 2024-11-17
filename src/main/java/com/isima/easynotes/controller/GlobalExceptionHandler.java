package com.isima.easynotes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.isima.easynotes.exception.NoSuchElementFoundException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoSuchElementFoundException(
			NoSuchElementFoundException exception){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(exception.getMessage());
	}
}