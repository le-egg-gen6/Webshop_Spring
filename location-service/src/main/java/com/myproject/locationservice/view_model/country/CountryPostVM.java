package com.myproject.locationservice.view_model.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:34 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryPostVM {

	@NotBlank
	@Size(min = 1, max = 500)
	private String name;

	@NotBlank
	@Size(max = 2)
	private String alpha2Code;

	@NotBlank
	@Size(max = 3)
	private String alpha3Code;

	private Boolean billingEnabled;

	private Boolean shippingEnabled;

	private Boolean cityEnabled;

	private Boolean zipCodeEnabled;

	private Boolean districtEnabled;
}
