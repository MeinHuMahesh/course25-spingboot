package com.example.demo.Exception;


import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.DTO.Errors;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Errors> feildExceptions (MethodArgumentNotValidException ex){ 
		StringBuilder message = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			message.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
		});
		Errors er = new Errors();
		er.setMessage(message.toString());
		er.setError("Validation Error");
		er.setStatus(HttpStatus.BAD_REQUEST.value());
		er.setTimestamp(Instant.now().toString());
		return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Errors> handleDataIntegrityException(DataIntegrityViolationException ex){
		Errors error = new Errors();
		String message =ex.getMostSpecificCause().getMessage();
		if (message.contains("course_course_code_key")) {
	        error.setMessage("Course code already exists. Please use a different course code.");
	    } else {
	        error.setMessage("Data integrity violation occurred.");
	    }
		error.setError("Data Integrity Violation !!");
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setTimestamp(Instant.now().toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InstanceAlreadyExistException.class)
	public ResponseEntity<Errors> handleInstanceExistException(InstanceAlreadyExistException ex){
		Instant timestamp =Instant.now();
		Errors error = new Errors();
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		error.setError("Duplicate Error");
		error.setTimestamp(timestamp.toString());
		return new ResponseEntity<Errors>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InstanceNotFoundException.class)
	public ResponseEntity<Errors> handleInstanceNotFoundException(InstanceNotFoundException ex){
		Errors error = new Errors();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not Found");
		error.setTimestamp(Instant.now().toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<Errors> handleCourseNotFoundException(CourseNotFoundException ex){
		Errors error = new Errors();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not Found");
		error.setTimestamp(Instant.now().toString());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Errors> handleRuntimeException(RuntimeException ex){
		Errors error = new Errors();
		error.setMessage(ex.getMessage());
		error.setError("ERROR");
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(Instant.now().toString());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
