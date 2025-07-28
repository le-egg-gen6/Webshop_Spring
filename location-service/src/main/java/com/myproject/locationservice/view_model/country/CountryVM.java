package com.myproject.locationservice.view_model.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:33 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryVM {

	private Long id;

	private String name;

	private String alpha2Code;

	private String alpha3Code;

	private Boolean billingEnabled;

	private Boolean shippingEnabled;

	private Boolean cityEnabled;

	private Boolean zipCodeEnabled;

	private Boolean districtEnabled;
}
