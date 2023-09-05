package com.codewithsameer.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithsameer.blog.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourceNotFoundexceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		APIResponse apiResponse = new APIResponse(message, false);
		return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionhandler(MethodArgumentNotValidException ex) {
		Map<String,String> resp =new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors)->{
			
			String fieldName=((FieldError)errors).getField();
			String message =errors.getDefaultMessage();
			resp.put(fieldName, message);			
		});		
		return new ResponseEntity<Map<String,String>> (resp, HttpStatus.BAD_REQUEST);
	}
	

}
