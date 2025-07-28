package com.myproject.mediaservice.exception;

import com.myproject.commonlibs.exception.AbstractControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author nguyenle
 * @since 4:51 PM Thu 7/24/2025
 */
@Slf4j
@RestControllerAdvice
public class MediaControllerAdvice extends AbstractControllerAdvice {

	@Autowired
	public MediaControllerAdvice(MessageSource messageSource) {
		super(messageSource);
	}

	@ExceptionHandler(UnsupportedMediaTypeException.class)
	public ResponseEntity<?> handleUnsupportedMediaTypeException(UnsupportedMediaTypeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		String message = "File upload are not supported";

		return buildErrorResponse(status, message, "Unsupported media type", null, ex, request);
	}

}
