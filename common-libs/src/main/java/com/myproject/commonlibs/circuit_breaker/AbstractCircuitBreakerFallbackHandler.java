package com.myproject.commonlibs.circuit_breaker;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nguyenle
 * @since 4:38 PM Mon 7/28/2025
 */
@Slf4j
public abstract class AbstractCircuitBreakerFallbackHandler {

	protected Object handleFallback(Throwable throwable) throws Throwable {
		handleError(throwable);
		return null;
	}

	private void handleError(Throwable throwable) throws Throwable {
		log.error("Circuit breaker records an error. Detail {}", throwable.getMessage());
		throw throwable;
	}

}
