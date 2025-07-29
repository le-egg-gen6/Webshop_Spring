package com.myproject.locationservice.view_model.district;

import jakarta.validation.constraints.Size;
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
public class DistrictPostVM {

	@Size(min = 1, max = 500)
	private String name;

	@Size(max = 500)
	private String type;

	@Size(max = 500)
	private String location;

	private Long stateProvinceId;

}
