package com.myproject.locationservice.service;

import com.myproject.locationservice.model.District;
import com.myproject.locationservice.view_model.district.DistrictListVm;
import com.myproject.locationservice.view_model.district.DistrictPostVM;
import com.myproject.locationservice.view_model.district.DistrictVM;
import java.util.List;

/**
 * @author nguyenle
 * @since 4:03 PM Tue 7/29/2025
 */
public interface DistrictService {

	DistrictListVm getAll(int pageIndex, int pageSize);

	DistrictVM getById(Long id);

	List<DistrictVM> getAllByStateProvinceId(Long stateProvinceId);

	District createDistrict(DistrictPostVM districtVM);

	void updateDistrict(DistrictPostVM districtVM, Long id);

	void deleteDistrict(Long id);

}
