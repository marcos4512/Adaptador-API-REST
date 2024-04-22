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
import com.prueba.adapter.exception.BadRequestException;
import com.prueba.adapter.exception.NotFoundException;

/**
 * @author Marcos Macías
 *
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExeptionsHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger LOG = LogManager.getLogger(ExeptionsHandler.class);
	
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<?> handleBadRequestException(Exception ex, WebRequest request) {
		LOG.error("Se recibió una petición errada", ex);
		ErrorResponseEntity errorResponse = new ErrorResponseEntity();
		errorResponse.setTimeStamp(Instant.now().toString());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<?> handleNotFoundException(Exception ex, WebRequest request) {
		LOG.warn("No se encontró el usuario consultado", ex);
		ErrorResponseEntity errorResponse = new ErrorResponseEntity();
		errorResponse.setTimeStamp(Instant.now().toString());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		errorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
