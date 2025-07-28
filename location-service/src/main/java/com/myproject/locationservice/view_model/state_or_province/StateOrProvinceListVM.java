package com.myproject.locationservice.view_model.state_or_province;

import com.myproject.commonlibs.view_model.AbstractPagableObject;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 6:09 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateOrProvinceListVM extends AbstractPagableObject {
	private List<StateOrProvinceVM> stateOrProvinces;
}
