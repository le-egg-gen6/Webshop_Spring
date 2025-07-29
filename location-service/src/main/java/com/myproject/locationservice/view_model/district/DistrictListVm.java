package com.myproject.locationservice.view_model.district;

import com.myproject.commonlibs.view_model.AbstractPagableObject;
import java.util.List;
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
public class DistrictListVm extends AbstractPagableObject {
	List<DistrictVM> districts;
}
