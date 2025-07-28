package com.myproject.productservice.config;

import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author nguyenle
 * @since 10:47 AM Wed 7/23/2025
 */
@Configuration
@EnableJpaRepositories("com.myproject.productservice.repository")
@EntityScan({"com.myproject.productservice.model", "com.myproject.productservice.model.attribute"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DatabaseAuditorAwareConfig {

	@Bean
	public AuditorAware<String> auditorAware() {
		return () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null) {
				return Optional.of("");
			}
			return Optional.of(authentication.getName());
		};
	}

}
