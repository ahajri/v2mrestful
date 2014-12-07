package com.ahajri.v2m.controller.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TechnicalExceptionHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(TechnicalExceptionHandler.class);

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		logger.info("SQLException Occured:: URL=" + request.getRequestURL());
		return "database_error: "+ex.getMessage();
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		logger.error("IOException handler executed");
		// returning 404 error code
	}

	
	
}
