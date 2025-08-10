package com.myproject.locationservice.view_model.district;

import com.myproject.locationservice.view_model.address.AddressVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 5:54 PM Mon 7/28/2025
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictVM {
	private Long id;

	private String name;

	private String type;

	private String location;

	private StateOrProvinceVM stateProvince;

	private List<AddressVM> addresses;
}

