package com.myproject.locationservice.view_model.district;

import com.myproject.locationservice.model.District;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:13 PM Tue 7/29/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictDetailVM {

	private Long id;

	private String name;

	private String type;

	private String location;

	private Long stateProvinceId;
	private String stateProvinceName;

	public static DistrictDetailVM fromModel(District district) {
		return DistrictDetailVM.builder()
			.id(district.getId())
			.name(district.getName())
			.type(district.getType())
			.location(district.getLocation())
			.stateProvinceId(district.getStateProvince().getId())
			.stateProvinceName(district.getStateProvince().getName())
			.build();
	}

}
