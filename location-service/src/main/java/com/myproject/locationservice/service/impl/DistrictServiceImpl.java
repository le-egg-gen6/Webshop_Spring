package com.myproject.locationservice.service.impl;

import com.myproject.locationservice.mapper.DistrictMapper;
import com.myproject.locationservice.repository.DistrictRepository;
import com.myproject.locationservice.service.DistrictService;
import com.myproject.locationservice.view_model.district.DistrictVM;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:04 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

	private final DistrictMapper districtMapper;

	private final DistrictRepository districtRepository;


	@Override
	public List<DistrictVM> getListDistrictInStateOrProvince(Long stateOrProvinceId) {
		return districtRepository.findAllByStateProvinceIdOrderByNameAsc(stateOrProvinceId)
			.stream()
			.map(districtMapper::toVmResponse)
			.toList();
	}
}
