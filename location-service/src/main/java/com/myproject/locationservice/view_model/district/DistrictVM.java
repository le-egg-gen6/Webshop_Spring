package com.myproject.locationservice.view_model.district;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	private Long stateOrProvinceId;
}

