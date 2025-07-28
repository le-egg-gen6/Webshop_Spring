package com.myproject.locationservice.view_model.state_or_province;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 6:10 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateOrProvinceDetailVM {
	private Long id;

	private String code;

	private String name;

	private String type;

	private Long countryId;
	private String countryName;
}
