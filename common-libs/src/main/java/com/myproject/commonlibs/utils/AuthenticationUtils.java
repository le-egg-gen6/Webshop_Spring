package com.myproject.commonlibs.utils;

import com.myproject.commonlibs.constant.ApiConstant;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * @author nguyenle
 * @since 3:26 PM Wed 7/23/2025
 */
@UtilityClass
public class AuthenticationUtils {

	public static String extractUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new AccountExpiredException(ApiConstant.ACCESS_DENIED);
		}

		JwtAuthenticationToken contextHolder = (JwtAuthenticationToken) authentication;
		return contextHolder.getToken().getSubject();
	}

	public static String extractId() {
		return ((Jwt) getAuthentication().getPrincipal()).getTokenValue();
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
