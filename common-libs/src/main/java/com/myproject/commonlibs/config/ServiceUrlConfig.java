package com.myproject.commonlibs.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author nguyenle
 * @since 2:21 PM Thu 7/24/2025
 */
@Configuration
@Getter
public class ServiceUrlConfig {

	@Value("${service.url}")
	private String serviceUrl;

}
