package com.myproject.locationservice.controller.backoffice;

import com.myproject.commonlibs.constant.PagingAndSortingConstant;
import com.myproject.commonlibs.view_model.ErrorVM;
import com.myproject.locationservice.mapper.CountryMapper;
import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.service.CountryService;
import com.myproject.locationservice.view_model.country.CountryPostVM;
import com.myproject.locationservice.view_model.country.CountryVM;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
 * @since 10:21 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/backoffice/countries")
@RequiredArgsConstructor
public class BackOfficeCountryController {

	private final CountryService countryService;

	private final CountryMapper countryMapper;

	@GetMapping
	private ResponseEntity<?> listCountries() {
		return ResponseEntity.ok(countryService.getAll());
	}

	@GetMapping("/paging")
	public ResponseEntity<?> getPageableCountries(
		@RequestParam(value = "pageIndex", defaultValue = PagingAndSortingConstant.DEFAULT_PAGE_NUMBER, required = false)
		int pageIndex,
		@RequestParam(value = "pageSize", defaultValue = PagingAndSortingConstant.DEFAULT_PAGE_SIZE, required = false)
		int pageSize
	) {
		return ResponseEntity.ok(countryService.getPageableCountries(pageIndex, pageSize));
	}

	@GetMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = CountryVM.class))),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> getCountry(@PathVariable("id") Long id) {
		return ResponseEntity.ok(countryService.getById(id));
	}

	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created",
			content = @Content(schema = @Schema(implementation = CountryVM.class))),
		@ApiResponse(responseCode = "400", description = "Bad request",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> createCountry(
		@Valid @RequestBody CountryPostVM countryPostVM,
		UriComponentsBuilder uriComponentsBuilder
	) {
		Country country = countryService.createCountry(countryPostVM);
		return ResponseEntity.created(uriComponentsBuilder
			.replacePath("/countries/{id}")
			.buildAndExpand(country.getId())
			.toUri()
		).body(countryMapper.toVmResponse(country));
	}

	@PutMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "No content",
			content = @Content()),
		@ApiResponse(responseCode = "404", description = "Not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class))),
		@ApiResponse(responseCode = "400", description = "Bad request",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> updateCountry(
		@PathVariable(name = "id") Long id,
		@Valid @RequestBody CountryPostVM countryPostVM
	) {
		countryService.updateCountry(countryPostVM, id);
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
	public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") Long id) {
		countryService.deleteCountry(id);
		return ResponseEntity.noContent().build();
	}

}
