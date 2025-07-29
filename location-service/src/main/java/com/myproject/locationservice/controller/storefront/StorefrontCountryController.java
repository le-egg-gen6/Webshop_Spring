package com.myproject.locationservice.controller.storefront;

import com.myproject.locationservice.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 10:18 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/storefront/countries")
@RequiredArgsConstructor
public class StorefrontCountryController {

	private final CountryService countryService;

	@GetMapping
	public ResponseEntity<?> listCountries() {
		return ResponseEntity.ok(countryService.findAllCountries());
	}

}
