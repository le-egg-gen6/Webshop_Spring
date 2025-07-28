package com.myproject.mediaservice.exception;

/**
 * @author nguyenle
 * @since 3:03 PM Fri 7/25/2025
 */
public class UnsupportedMediaTypeException extends RuntimeException {

	public UnsupportedMediaTypeException() {
		super();
	}

	public UnsupportedMediaTypeException(String message) {
		super(message);
	}

	public UnsupportedMediaTypeException(Throwable cause) {
		super(cause);
	}

	public UnsupportedMediaTypeException(String message, Throwable cause) {
		super(message, cause);
	}
}
