package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.exception.DuplicatedException;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.CountryMapper;
import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.repository.CountryRepository;
import com.myproject.locationservice.service.CountryService;
import com.myproject.locationservice.view_model.country.CountryListVM;
import com.myproject.locationservice.view_model.country.CountryPostVM;
import com.myproject.locationservice.view_model.country.CountryVM;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 10:26 AM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CountryServiceImpl implements CountryService {

	private static final String DEFAULT_COUNTRY_SORT_FIELD = "name";

	private final CountryRepository countryRepository;

	private final CountryMapper countryMapper;

	@Override
	@Transactional(readOnly = true)
	public List<CountryVM> findAllCountries() {
		return countryRepository
			.findAll(Sort.by(Direction.ASC, DEFAULT_COUNTRY_SORT_FIELD))
			.stream()
			.map(countryMapper::toVmResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CountryVM findById(Long id) {
		Country country = countryRepository.findById(id).orElseThrow(
			() -> new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, id)
		);
		return countryMapper.toVmResponse(country);
	}

	@Override
	@Transactional
	public Country create(CountryPostVM countryPostVM) {
		if (countryRepository.existsByAlpha2CodeIgnoreCase(countryPostVM.getAlpha2Code())) {
			throw new DuplicatedException(ExceptionConstant.CODE_ALREADY_EXISTED, countryPostVM.getAlpha2Code());
		}
		if (countryRepository.existsByNameIgnoreCase(countryPostVM.getName())) {
			throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, countryPostVM.getName());
		}
		return countryRepository.save(countryMapper.toModel(countryPostVM));
	}

	@Override
	@Transactional
	public void update(CountryPostVM countryPostVM, Long id) {
		Country country = countryRepository
			.findById(id)
			.orElseThrow(() -> new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, id));
		if (countryRepository.existsByAlpha2CodeIgnoreCase(countryPostVM.getAlpha2Code())) {
			throw new DuplicatedException(ExceptionConstant.CODE_ALREADY_EXISTED, countryPostVM.getAlpha2Code());
		}
		if (countryRepository.existsByNameIgnoreCase(countryPostVM.getName())) {
			throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, countryPostVM.getName());
		}
		countryMapper.partialUpdate(country, countryPostVM);
		countryRepository.save(country);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		boolean isCountryExisted = countryRepository.existsById(id);
		if (!isCountryExisted) {
			throw new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, id);
		}
		countryRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public CountryListVM getPageableCountries(int pageIndex, int pageSize) {
		Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(Direction.ASC, DEFAULT_COUNTRY_SORT_FIELD));
		Page<Country> countries = countryRepository.findAll(pageable);
		List<Country> countriesList = countries.getContent();

		List<CountryVM> countryVMs = countriesList
			.stream()
			.map(countryMapper::toVmResponse)
			.toList();

		CountryListVM countryListVM = new CountryListVM();
		countryListVM.applyPageableMetadata(countries);
		countryListVM.setCountries(countryVMs);
		return countryListVM;
	}


}
