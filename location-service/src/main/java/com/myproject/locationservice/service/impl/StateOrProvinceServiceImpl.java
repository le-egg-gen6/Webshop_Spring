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

/**
 * @author nguyenle
 * @since 2:10 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StateOrProvinceServiceImpl implements StateOrProvinceService {

	private static final String DEFAULT_STATE_OR_PROVINCE_SORT_FIELD = "name";

	private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

	private final StateOrProvinceRepository stateOrProvinceRepository;
	private final CountryRepository countryRepository;

	private final StateOrProvinceMapper stateOrProvinceMapper;


	@Override
	public StateOrProvinceVM getById(Long id) {
		StateOrProvince stateOrProvince = stateOrProvinceRepository.findById(id)
			.orElseThrow(
				() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id)
			);
		StateOrProvinceVM vm = stateOrProvinceMapper.toVmResponse(stateOrProvince);
		vm.setCountryId(stateOrProvince.getCountry().getId());
		return vm;
	}

	@Override
	public List<StateOrProvinceVM> getAll() {
		return stateOrProvinceRepository.findAll()
			.stream()
			.map(stateOrProvince -> {
				StateOrProvinceVM vm = stateOrProvinceMapper.toVmResponse(stateOrProvince);
				vm.setCountryId(stateOrProvince.getCountry().getId());
				return vm;
			})
			.toList();
	}

	@Override
	public List<StateOrProvinceVM> getAllByCountryId(Long countryId) {
		return stateOrProvinceRepository.findByCountryIdOrderByNameAsc(countryId)
			.stream()
			.map(stateOrProvince -> {
				StateOrProvinceVM vm = stateOrProvinceMapper.toVmResponse(stateOrProvince);
				vm.setCountryId(countryId);
				return vm;
			})
			.toList();
	}

	@Override
	public List<StateOrProvinceDetailVM> getStateOrProvinceWithCountryName(List<Long> stateOrProvinceIds) {
		List<StateOrProvince> stateOrProvinces = stateOrProvinceRepository.findByIdIn(stateOrProvinceIds);
		return stateOrProvinces.stream()
			.map(StateOrProvinceDetailVM::fromModel)
			.toList();
	}

	@Override
	public StateOrProvinceListVM getPageableStateOrProvinceByCountryId(int pageIndex, int pageSize, Long countryId) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_STATE_OR_PROVINCE_SORT_FIELD));
		Page<StateOrProvince> stateOrProvincesPage = stateOrProvinceRepository.findByCountryId(countryId, pageable);
		List<StateOrProvince> stateOrProvinceList = stateOrProvincesPage.getContent();

		StateOrProvinceListVM stateOrProvinceListVM = new StateOrProvinceListVM();
		stateOrProvinceListVM.applyPageableMetadata(stateOrProvincesPage);
		stateOrProvinceListVM.setStateOrProvinces(
			stateOrProvinceList.stream().map(stateOrProvince -> {
					StateOrProvinceVM vm = stateOrProvinceMapper.toVmResponse(stateOrProvince);
					vm.setCountryId(countryId);
					return vm;
				}
			).toList());
		return stateOrProvinceListVM;
	}

	@Override
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
	public void updateStateOrProvince(StateOrProvincePostVM stateOrProvinceVM, Long id) {
		StateOrProvince stateOrProvince = stateOrProvinceRepository.findById(id)
			.orElseThrow(
				() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id)
			);

		if (stateOrProvinceRepository.existsByNameIgnoreCaseAndCountryIdAndIdNot(stateOrProvinceVM.getName(), stateOrProvince.getCountry().getId(), id)) {
			throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, stateOrProvinceVM.getName());
		}

		stateOrProvinceMapper.partialUpdate(stateOrProvince, stateOrProvinceVM);

		stateOrProvinceRepository.save(stateOrProvince);
	}

	@Override
	public void deleteStateOrProvince(Long id) {
		boolean isStateOrProvinceExist = stateOrProvinceRepository.existsById(id);
		if (!isStateOrProvinceExist) {
			throw new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id);
		}
		stateOrProvinceRepository.deleteById(id);
	}


}
