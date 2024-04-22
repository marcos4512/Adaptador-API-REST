/**
 * 
 */
package com.prueba.adapter.controller;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prueba.adapter.dto.ErrorResponseEntity;

/**
 * @author Marcos Macías
 *
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LogManager.getLogger(GeneralExceptionHandler.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
		LOG.error("Se presentó un error no controlado que resultó en error del aplicativo", ex);
		ErrorResponseEntity errorResponse = new ErrorResponseEntity();
		errorResponse.setTimeStamp(Instant.now().toString());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
