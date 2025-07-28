package com.myproject.locationservice.view_model.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:52 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetailVM {

	private Long id;

	private String contactName;

	private String phone;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String zipCode;

	private Long districtId;
	private String districtName;

	private Long stateOrProvinceId;
	private String stateOrProvinceName;

	private Long countryId;
	private String countryName;

}
