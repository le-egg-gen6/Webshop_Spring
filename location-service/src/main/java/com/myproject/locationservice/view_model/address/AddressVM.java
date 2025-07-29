package com.myproject.locationservice.view_model.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:45 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressVM {

	private Long id;

	private String contactName;

	private String phone;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String zipCode;

	private Long districtId;

	private Long stateProvinceId;

	private Long countryId;
}
