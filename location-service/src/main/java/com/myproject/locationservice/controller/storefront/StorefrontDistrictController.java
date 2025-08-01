package com.myproject.locationservice.controller.storefront;

import com.myproject.commonlibs.view_model.ErrorVM;
import com.myproject.locationservice.model.District;
import com.myproject.locationservice.service.DistrictService;
import com.myproject.locationservice.view_model.district.DistrictVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 10:19 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/storefront/districts")
@RequiredArgsConstructor
public class StorefrontDistrictController {

	private final DistrictService districtService;

	@GetMapping("/{stateOrProvinceId}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = DistrictVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> getListDistrict(@PathVariable Long stateOrProvinceId) {
		return ResponseEntity.ok(districtService.getListDistrictInStateOrProvince(stateOrProvinceId));
	}

}
