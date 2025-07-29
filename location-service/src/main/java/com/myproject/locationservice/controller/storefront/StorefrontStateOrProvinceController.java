package com.myproject.locationservice.controller.storefront;

import com.myproject.commonlibs.view_model.ErrorVM;
import com.myproject.locationservice.service.StateOrProvinceService;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 10:20 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/storefront/states-or-provinces")
@RequiredArgsConstructor
public class StorefrontStateOrProvinceController {

	private final StateOrProvinceService stateOrProvinceService;

	@GetMapping("/{countryId}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = StateOrProvinceVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> getStateOrProvince(
		@PathVariable("countryId") Long countryId
	) {
		return ResponseEntity.ok(stateOrProvinceService.getAllByCountryId(countryId));
	}
}
