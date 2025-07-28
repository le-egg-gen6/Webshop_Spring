package com.myproject.commonlibs.exception;

import com.myproject.commonlibs.config.MessageSourceConfig;
import com.myproject.commonlibs.utils.MessageUtils;

/**
 * @author nguyenle
 * @since 1:27 PM Fri 7/25/2025
 */
public class NotFoundException extends RuntimeException {

	private final String message;

	public NotFoundException(String errorCode, Object... vars) {
		this.message = MessageUtils.getMessage(
			MessageSourceConfig.getMessageSourceReference(),
			errorCode,
			vars
		);
	}

	@Override
	public String getMessage() {
		return message;
	}
}
