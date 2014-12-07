package com.ahajri.v2m.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Technical Problem")
public class TechnicalException extends Exception {

	/** UUID */
	private static final long serialVersionUID = 8504598614347343962L;

	public TechnicalException(Throwable ex) {
		super("Technical error due to: " + ex.getMessage());
	}

	private int code;
	private String message;

	public TechnicalException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
