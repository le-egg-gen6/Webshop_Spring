package com.myproject.commonlibs.config;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author nguyenle
 * @since 10:52 AM Fri 7/25/2025
 */
@Slf4j
@Configuration
public class MessageSourceConfig {

	private static final String DEFAULT_MESSAGE_SOURCE_BASE_NAME = "classpath:messages";

	private static final String DEFAULT_MESSAGE_SOURCE_DEFAULT_ENCODING = "UTF-8";

	private static final int RELOAD_DELAY_SECONDS = Math.toIntExact(TimeUnit.HOURS.toSeconds(1));

	@Getter
	private static MessageSource messageSourceReference;

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasenames(DEFAULT_MESSAGE_SOURCE_BASE_NAME);
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setDefaultEncoding(DEFAULT_MESSAGE_SOURCE_DEFAULT_ENCODING);
		messageSource.setCacheSeconds(RELOAD_DELAY_SECONDS);
		messageSource.setUseCodeAsDefaultMessage(true);

		log.info("Loaded MessageSource: {}", messageSource);

		messageSourceReference = messageSource;

		return messageSource;
	}

}
