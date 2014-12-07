package com.ahajri.v2m.controller;

import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author ahajri
 *
 */
public abstract class AController {

	/**
	 * 
	 * @param headers
	 */
	protected void permitRemoteWScall(HttpHeaders headers) {
		headers.add("Content-Type", "application/json; charset=utf-8");
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Access-Control-Max-Age", "1800");// 30 min
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request,
			HttpHeaders headers, Exception ex) {
		ex.printStackTrace();
		request.setAttribute("exception", ex.getMessage());
		// return new ResponseEntity<String>(ex.toString(), headers,
		// HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(javax.persistence.TransactionRequiredException.class)
	public ResponseEntity<String> handleTransactionRequiredException(
			HttpServletRequest request, TransactionRequiredException ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
