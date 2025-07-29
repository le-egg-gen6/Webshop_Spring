package com.myproject.locationservice.controller.backoffice;

import com.myproject.commonlibs.constant.PagingConstant;
import com.myproject.commonlibs.view_model.ErrorVM;
import com.myproject.locationservice.mapper.StateOrProvinceMapper;
import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.service.StateOrProvinceService;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceDetailVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvincePostVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author nguyenle
 * @since 10:23 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/backoffice/states-or-provinces")
@RequiredArgsConstructor
public class BackOfficeStateOrProvinceController {

	private final StateOrProvinceService stateOrProvinceService;

	private final StateOrProvinceMapper stateOrProvinceMapper;

	@GetMapping("/paging")
	public ResponseEntity<?> getPageableStateOrProvinces(
		@RequestParam(value = "pageIndex", defaultValue = PagingConstant.DEFAULT_PAGE_NUMBER, required = false)
		int pageIndex,
		@RequestParam(value = "pageSize", defaultValue = PagingConstant.DEFAULT_PAGE_SIZE, required = false)
		int pageSize,
		@RequestParam(value = "countryId", required = false)
		Long countryId
	) {
		return ResponseEntity.ok(stateOrProvinceService.getPageableStateOrProvinceByCountryId(pageIndex, pageSize, countryId));
	}

	@GetMapping
	public ResponseEntity<?> getAllStateOrProvincesByCountryId(
		@RequestParam(value = "countryId", required = false) Long countryId
	) {
		return ResponseEntity.ok(stateOrProvinceService.getAllByCountryId(countryId));
	}

	@GetMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = StateOrProvinceVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> getStateOrProvince(@PathVariable("id") Long id) {
		return ResponseEntity.ok(stateOrProvinceService.getById(id));
	}

	@GetMapping("state-country-names")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = StateOrProvinceDetailVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> getStateOrProvinceAndCountryNames(
		@RequestParam(value = "stateOrProvinceIds") List<Long> stateOrProvinceIds) {
		return ResponseEntity.ok(stateOrProvinceService.getStateOrProvinceWithCountryName(stateOrProvinceIds));
	}

	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created",
			content = @Content(schema = @Schema(implementation = StateOrProvinceVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> createStateOrProvince(
		@Valid @RequestBody StateOrProvincePostVM stateOrProvincePostVm,
		UriComponentsBuilder uriComponentsBuilder
	) {
		StateOrProvince stateOrProvince = stateOrProvinceService.createStateOrProvince(stateOrProvincePostVm);

		return ResponseEntity.created(uriComponentsBuilder.replacePath("/state-or-provinces/{id}")
				.buildAndExpand(stateOrProvince.getId()).toUri())
			.body(stateOrProvinceMapper.toVmResponse(stateOrProvince));
	}

	@PutMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "No content",
			content = @Content()),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class))),
		@ApiResponse(responseCode = "400", description = "Bad request",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> updateStateOrProvince(
		@PathVariable Long id,
		@Valid @RequestBody StateOrProvincePostVM stateOrProvincePostVm
	) {
		stateOrProvinceService.updateStateOrProvince(stateOrProvincePostVm, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "No content",
			content = @Content()),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class))),
		@ApiResponse(responseCode = "400", description = "Bad request",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> deleteStateOrProvince(@PathVariable Long id) {
		stateOrProvinceService.deleteStateOrProvince(id);
		return ResponseEntity.noContent().build();
	}

}
