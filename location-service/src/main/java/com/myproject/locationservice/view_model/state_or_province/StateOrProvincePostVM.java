package com.myproject.locationservice.view_model.state_or_province;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 6:05 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateOrProvincePostVM {

	@Size(max = 255)
	private String code;

	@Size(min = 1, max = 500)
	private String name;

	@Size(max = 255)
	private String type;

	private Long countryId;
}
