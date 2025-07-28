package com.myproject.mediaservice.config;

import com.myproject.commonlibs.constant.SecurityConstant;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author nguyenle
 * @since 9:41 AM Thu 7/24/2025
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(AbstractHttpConfigurer::disable)
//			.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/actuator/prometheus", "/actuator/health/**",
//					"/swagger-ui", "/swagger-ui/**", "/error", "/v3/api-docs/**").permitAll()
//				.requestMatchers(HttpMethod.GET, "/medias/**").permitAll()
//				.requestMatchers("/medias").hasRole("ADMIN")
//				.anyRequest().authenticated()
//			)
//			.oauth2ResourceServer(oauth2 -> oauth2.jwt(
//				jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
//			))
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
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
