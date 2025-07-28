package com.myproject.locationservice.view_model.address;

import com.myproject.commonlibs.annotation.validation.RegexMatchMode;
import com.myproject.commonlibs.annotation.validation.StringRegexValid;
import com.myproject.commonlibs.constant.StringRegexConstant;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:47 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressPostVM {
	@Size(max = 500)
	private String contactName;

	@Size(max = 30)
	@StringRegexValid(
		regexes = StringRegexConstant.PHONE,
		matchMode = RegexMatchMode.ALL
	)
	private String phone;

	@Size(max = 500)
	private String addressLine1;

	@Size(max = 500)
	private String addressLine2;

	@Size(max = 500)
	private String city;

	@Size(max = 30)
	private String zipCode;

	private Long districtId;

	private Long stateOrProvinceId;

	private Long countryId;
}
