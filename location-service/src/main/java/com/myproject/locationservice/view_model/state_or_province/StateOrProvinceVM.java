package com.myproject.locationservice.view_model.state_or_province;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myproject.locationservice.view_model.country.CountryVM;
import com.myproject.locationservice.view_model.district.DistrictVM;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 6:04 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateOrProvinceVM {
	private Long id;

	private String code;

	private String name;

	private String type;

	private CountryVM country;

	private List<DistrictVM> districts;
}
