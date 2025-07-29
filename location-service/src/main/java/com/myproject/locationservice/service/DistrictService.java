package com.myproject.locationservice.service;

import com.myproject.locationservice.view_model.district.DistrictVM;
import java.util.List;

/**
 * @author nguyenle
 * @since 4:03 PM Tue 7/29/2025
 */
public interface DistrictService {

	DistrictVM getById(Long id);

	List<DistrictVM> getAll();

	List<DistrictVM> getListDistrictInStateOrProvince(Long stateOrProvinceId);



}
