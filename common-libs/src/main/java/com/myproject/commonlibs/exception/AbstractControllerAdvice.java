package com.myproject.commonlibs.exception;

import com.myproject.commonlibs.view_model.ErrorVM;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author nguyenle
 * @since 5:20 PM Thu 7/24/2025
 */
@Slf4j
public abstract class AbstractControllerAdvice {

	protected final String ERROR_LOG_FORMAT = "Error: URI: {}, ErrorCode: {}, Message: {}";

	protected final MessageSource messageSource;

	public AbstractControllerAdvice(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		List<String> errors = ex.getConstraintViolations().stream()
			.map(violation -> String.format("%s %s: %s",
				violation.getRootBeanClass().getName(),
				violation.getPropertyPath(),
				violation.getMessage()))
			.toList();

		return buildErrorResponse(status, "Request information is not valid", errors, ex, request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<String> errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(error -> error.getField() + " " + error.getDefaultMessage())
			.toList();

		return buildErrorResponse(status, "Request information is not valid", errors, ex, request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = ex.getMessage();

		return buildErrorResponse(status, message, null, ex, request);
	}

	@ExceptionHandler(DuplicatedException.class)
	public ResponseEntity<?> handleDuplicatedException(DuplicatedException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = ex.getMessage();

		return buildErrorResponse(status, message, null, ex, request);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return buildErrorResponse(status, message, null, e, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return buildErrorResponse(status, message, null, e, request);
	}

	protected ResponseEntity<?> buildErrorResponse(
		HttpStatus status,
		String message,
		String title,
		List<String> errors,
		Exception ex,
		HttpServletRequest request,
		int statusCode
	) {
		ErrorVM errorVM = ErrorVM.builder()
			.statusCode(status.toString())
			.title(StringUtils.isEmpty(title) ? status.getReasonPhrase() : title)
			.detail(message)
			.fieldErrors(errors)
			.build();
		if (request != null) {
			log.error(ERROR_LOG_FORMAT, getServletPath(request), statusCode, errorVM);
		}
		log.error(message, ex);
		return ResponseEntity.status(statusCode).body(errorVM);
	}

	protected ResponseEntity<?> buildErrorResponse(
		HttpStatus status,
		String message,
		List<String> errors,
		Exception ex,
		HttpServletRequest request
	) {
		return buildErrorResponse(status, message, null, errors, ex, request, status.value());
	}

	protected ResponseEntity<?> buildErrorResponse(
		HttpStatus status,
		String message,
		String title,
		List<String> errors,
		Exception ex,
		HttpServletRequest request
	) {
		return buildErrorResponse(status, message, title, errors, ex, request, status.value());
	}

	protected String getServletPath(HttpServletRequest request) {
		return request.getServletPath();
	}
}
