package com.myproject.locationservice.view_model.country;

import com.myproject.commonlibs.view_model.AbstractPagableObject;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 5:37 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryListVM extends AbstractPagableObject {
	private List<CountryVM> countries;
}
