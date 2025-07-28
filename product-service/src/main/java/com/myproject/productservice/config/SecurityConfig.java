package com.myproject.productservice.config;

import com.myproject.commonlibs.constant.SecurityConstant;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author nguyenle
 * @since 4:45 PM Wed 7/23/2025
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
			.authorizeHttpRequests(auth -> auth
				.anyRequest().authenticated()
			)
			.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
			.build();
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
			Map<String, Collection<String>> realmAccess = jwt.getClaim(SecurityConstant.JWT_REALM_ACCESS_ATTRIBUTE_KEY);
			Collection<String> roles = realmAccess.get(SecurityConstant.JWT_ROLES_ATTRIBUTE_KEY);
			return roles.stream()
				.map(role -> new SimpleGrantedAuthority(
					String.format(SecurityConstant.ROLE_PATTERN, role)
				))
				.collect(Collectors.toList());
		};

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

		return jwtAuthenticationConverter;
	}

}
