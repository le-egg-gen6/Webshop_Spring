package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.exception.DuplicatedException;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.StateOrProvinceMapper;
import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.repository.CountryRepository;
import com.myproject.locationservice.repository.StateOrProvinceRepository;
import com.myproject.locationservice.service.StateOrProvinceService;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceDetailVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceListVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvincePostVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 2:10 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StateOrProvinceServiceImpl implements StateOrProvinceService {

	private static final String DEFAULT_STATE_OR_PROVINCE_SORT_FIELD = "name";

	private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

	private final StateOrProvinceRepository stateOrProvinceRepository;
	private final CountryRepository countryRepository;

	private final StateOrProvinceMapper stateOrProvinceMapper;


	@Override
	@Transactional(readOnly = true)
	public StateOrProvinceVM findById(Long id) {
		StateOrProvince stateOrProvince = stateOrProvinceRepository.findById(id)
			.orElseThrow(
				() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id)
			);
		return stateOrProvinceMapper.toVmResponse(stateOrProvince);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StateOrProvinceVM> findAll() {
		return stateOrProvinceRepository.findAll()
			.stream()
			.map(stateOrProvinceMapper::toVmResponse)
			.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<StateOrProvinceVM> getAllByCountryId(Long countryId) {
		return stateOrProvinceRepository.findByCountryIdOrderByNameAsc(countryId)
			.stream()
			.map(stateOrProvinceMapper::toVmResponse)
			.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<StateOrProvinceDetailVM> getStateOrProvinceWithCountryName(List<Long> stateOrProvinceIds) {
		List<StateOrProvince> stateOrProvinces = stateOrProvinceRepository.findByIdWithPreFetchCountry(stateOrProvinceIds);
		return stateOrProvinces.stream()
			.map(StateOrProvinceDetailVM::fromModel)
			.toList();
	}

	@Override
	@Transactional
	public StateOrProvinceListVM getPageableStateOrProvinceByCountryId(int pageIndex, int pageSize, Long countryId) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_STATE_OR_PROVINCE_SORT_FIELD));
		Page<StateOrProvince> stateOrProvincesPage = stateOrProvinceRepository.findByCountryId(countryId, pageable);
		List<StateOrProvince> stateOrProvinceList = stateOrProvincesPage.getContent();

		StateOrProvinceListVM stateOrProvinceListVM = new StateOrProvinceListVM();
		stateOrProvinceListVM.applyPageableMetadata(stateOrProvincesPage);
		stateOrProvinceListVM.setStateOrProvinces(
			stateOrProvinceList.stream().map(stateOrProvinceMapper::toVmResponse).toList()
		);
		return stateOrProvinceListVM;
	}

	@Override
	@Transactional
	public StateOrProvince createStateOrProvince(StateOrProvincePostVM stateOrProvinceVM) {
		Long countryId = stateOrProvinceVM.getCountryId();
		boolean isCountryExist = countryRepository.existsById(countryId);
		if (!isCountryExist) {
			throw new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, countryId);
		}
		if (stateOrProvinceRepository.existsByNameIgnoreCaseAndCountryId(stateOrProvinceVM.getName(), countryId)) {
			throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, stateOrProvinceVM.getName());
		}
		StateOrProvince stateOrProvince = stateOrProvinceMapper.toModel(stateOrProvinceVM);
		stateOrProvince.setCountry(countryRepository.getReferenceById(countryId));
		return stateOrProvinceRepository.save(stateOrProvince);
	}

	@Override
	@Transactional
	public void updateStateOrProvince(StateOrProvincePostVM stateOrProvinceVM, Long id) {
		StateOrProvince stateOrProvince = stateOrProvinceRepository.findById(id)
			.orElseThrow(
				() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id)
			);

		if (stateOrProvinceRepository.existsByNameIgnoreCaseAndCountryIdAndIdNot(stateOrProvinceVM.getName(), stateOrProvince.getCountry().getId(), id)) {
			throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, stateOrProvinceVM.getName());
		}

		stateOrProvince.setName(stateOrProvinceVM.getName());
		stateOrProvince.setCode(stateOrProvinceVM.getCode());
		stateOrProvince.setType(stateOrProvinceVM.getType());

		stateOrProvinceRepository.save(stateOrProvince);
	}

	@Override
	@Transactional
	public void deleteStateOrProvince(Long id) {
		boolean isStateOrProvinceExist = stateOrProvinceRepository.existsById(id);
		if (!isStateOrProvinceExist) {
			throw new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id);
		}
		stateOrProvinceRepository.deleteById(id);
	}


}
