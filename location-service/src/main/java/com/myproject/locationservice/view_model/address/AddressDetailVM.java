package com.myproject.locationservice.view_model.address;

import com.myproject.locationservice.model.Address;
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

	public static AddressDetailVM fromModel(Address address) {
		return AddressDetailVM.builder()
			.id(address.getId())
			.contactName(address.getContactName())
			.phone(address.getPhone())
			.addressLine1(address.getAddressLine1())
			.addressLine2(address.getAddressLine2())
			.city(address.getCity())
			.districtId(address.getDistrict().getId())
			.districtName(address.getDistrict().getName())
			.stateOrProvinceId(address.getStateOrProvince().getId())
			.stateOrProvinceName(address.getStateOrProvince().getName())
			.countryId(address.getCountry().getId())
			.countryName(address.getCountry().getName())
			.zipCode(address.getZipCode())
			.build();
	}

}
