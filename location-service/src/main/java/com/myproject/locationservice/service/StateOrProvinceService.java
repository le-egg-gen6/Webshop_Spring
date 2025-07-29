package com.myproject.locationservice.service;

import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceDetailVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceListVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvincePostVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import java.util.List;

/**
 * @author nguyenle
 * @since 2:09 PM Tue 7/29/2025
 */
public interface StateOrProvinceService {

	StateOrProvinceVM getById(Long id);

	List<StateOrProvinceVM> getAll();

	List<StateOrProvinceVM> getAllByCountryId(Long countryId);

	List<StateOrProvinceDetailVM> getStateOrProvinceWithCountryName(List<Long> stateOrProvinceIds);

	StateOrProvinceListVM getPageableStateOrProvinceByCountryId(int pageIndex, int pageSize, Long countryId);

	StateOrProvince createStateOrProvince(StateOrProvincePostVM stateOrProvinceVM);

	void updateStateOrProvince(StateOrProvincePostVM stateOrProvinceVM, Long id);

	void deleteStateOrProvince(Long id);
}
