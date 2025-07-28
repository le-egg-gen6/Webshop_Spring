package com.myproject.commonlibs.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author nguyenle
 * @since 11:30 AM Wed 7/23/2025
 */
@Configuration
public class CorsConfig {

	@Value("${cors.allowed-origins}")
	private String allowedOrigins;

	@Value("${cors.allowed-methods}")
	private String allowedMethods;

	@Value("${cors.allowed-headers}")
	private String allowedHeaders;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**")
					.maxAge(3600)
					.allowedOrigins(allowedOrigins)
					.allowedMethods(allowedMethods)
					.allowedHeaders(allowedHeaders);
			}
		};
	}
}
