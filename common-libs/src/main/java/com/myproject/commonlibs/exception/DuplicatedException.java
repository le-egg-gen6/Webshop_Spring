package com.myproject.commonlibs.exception;

import com.myproject.commonlibs.config.MessageSourceConfig;
import com.myproject.commonlibs.utils.MessageUtils;

/**
 * @author nguyenle
 * @since 10:42 AM Tue 7/29/2025
 */
public class DuplicatedException extends RuntimeException {
	private final String message;

	public DuplicatedException(String errorCode, Object... vars) {
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
