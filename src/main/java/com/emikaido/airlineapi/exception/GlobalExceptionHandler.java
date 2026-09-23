package com.emikaido.airlineapi.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
	        MethodArgumentNotValidException ex) {
	    // ① Mapを作る
		Map<String, String> errors = new HashMap<>();
		
	    // ② FieldErrorを取得
		List<FieldError> fieldErrors =
		        ex.getBindingResult().getFieldErrors();

	    // ③ forで1件ずつMapにput
		for (FieldError fieldError : fieldErrors) {
			errors.put(
					fieldError.getField(),
					fieldError.getDefaultMessage()
		    		);
		}
	    // ④ HTTP 400 + Mapを返す
		return ResponseEntity
		        .status(HttpStatus.BAD_REQUEST)
		        .body(errors);
	}
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<String> handleFlightNotFound(
			FlightNotFoundException ex) {

		String errorMessage = ex.getMessage();

		return ResponseEntity
		        .status(HttpStatus.NOT_FOUND)
		        .body(errorMessage);
	}

}
