package com.myproject.mediaservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author nguyenle
 * @since 5:46 PM Fri 7/25/2025
 */
@OpenAPIDefinition(
	info = @Info(
		title = "Media Service API",
		description = "Media API documentation",
		version = "1.0"
	),
	security = @SecurityRequirement(name = "oauth2_bearer"),
	servers = @Server(url = "${service.url}", description = "Default Server URL")
)
//@SecurityScheme(
//	name = "oauth2_bearer",
//	type = SecuritySchemeType.OAUTH2,
//	flows = @OAuthFlows(
//		authorizationCode = @OAuthFlow(
//			authorizationUrl = "${springdoc.oauthflow.authorization-url}",
//			tokenUrl = "${springdoc.oauthflow.token-url}",
//			scopes = @OAuthScope(name = "openid", description = "openid")
//		)
//	)
//)
public class SwaggerConfig {
}