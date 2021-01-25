package com.mutant.challenge.api.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;
import com.mutant.challenge.api.model.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * It is used for handling exceptions
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@ControllerAdvice
public class MutantChallengeExceptionHandler {

	/**
	 * Handles business logic exceptions.
	 * 
	 * @param ex Exception
	 * @return http response with error message
	 */
	@ExceptionHandler(value = { ArraySizeNotAllowedException.class, LetterSequenceNotAllowedException.class })
	public ResponseEntity<Object> handleBusinessException(Exception ex) {

		log.error("Business Exception: ", ex);

		ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(ex.getMessage()).build();
		log.error("ERROR: {}", errorResponse.getErrorMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles bad request exceptions.
	 * 
	 * @param ex invalid argument exception
	 * @return http response with error message
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {

		log.error("Method Argument Not Valid Exception: ", ex);

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles bad request exceptions.
	 * 
	 * @param ex invalid argument exception
	 * @return http response with error message
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handleValidationPath(ConstraintViolationException ex) {

		log.error("Constraint Violation Exception: ", ex);

		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(error -> errors.put("satelliteName", error.getMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Default exception.
	 * 
	 * @param ex exception
	 * @return http response with error message
	 */
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> defaultException(Exception ex) {

		log.error("Default Exception: ", ex);

		ErrorResponse errorResponse = ErrorResponse.builder().errorMessage("An exception error has occurred").build();
		log.error("ERROR: {}", errorResponse.getErrorMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
