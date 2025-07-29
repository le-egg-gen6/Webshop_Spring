package com.myproject.locationservice.exception;

import com.myproject.commonlibs.exception.AbstractControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author nguyenle
 * @since 10:40 AM Tue 7/29/2025
 */
@Slf4j
@RestControllerAdvice
public class LocationControllerAdvice extends AbstractControllerAdvice {

	@Autowired
	public LocationControllerAdvice(MessageSource messageSource) {
		super(messageSource);
	}
}
