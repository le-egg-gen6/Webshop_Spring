package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.AddressMapper;
import com.myproject.locationservice.model.Address;
import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.model.District;
import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.repository.AddressRepository;
import com.myproject.locationservice.repository.CountryRepository;
import com.myproject.locationservice.repository.DistrictRepository;
import com.myproject.locationservice.repository.StateOrProvinceRepository;
import com.myproject.locationservice.service.AddressService;
import com.myproject.locationservice.view_model.address.AddressDetailVM;
import com.myproject.locationservice.view_model.address.AddressPostVM;
import com.myproject.locationservice.view_model.address.AddressVM;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:17 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
//
//	private final AddressRepository addressRepository;
//	private final CountryRepository countryRepository;
//	private final StateOrProvinceRepository stateOrProvinceRepository;
//	private final DistrictRepository districtRepository;
//
//	private final AddressMapper addressMapper;
//
//	@Override
//	public AddressDetailVM getById(Long id) {
//		Address address = addressRepository.findByIdWithPreFetch(id).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id)
//		);
//		return AddressDetailVM.fromModel(address);
//	}
//
//	@Override
//	public List<AddressDetailVM> findAddressList(List<Long> ids) {
//		List<Address> addresses = addressRepository.findByIdInWithPreFetch(ids);
//		return addresses.stream()
//			.map(AddressDetailVM::fromModel)
//			.toList();
//	}
//
//	@Override
//	public AddressVM createAddress(AddressPostVM addressPostVM) {
//		Address address = addressMapper.toModel(addressPostVM);
//		Country country = countryRepository.getById(addressPostVM.getCountryId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, addressPostVM.getCountryId())
//		);
//		StateOrProvince stateOrProvince = stateOrProvinceRepository.getById(addressPostVM.getStateOrProvinceId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, addressPostVM.getStateOrProvinceId())
//		);
//		District district = districtRepository.getById(addressPostVM.getDistrictId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, addressPostVM.getDistrictId())
//		);
//		address.setCountry(country);
//		address.setStateProvince(stateOrProvince);
//		address.setDistrict(district);
//		addressRepository.save(address);
//
//		AddressVM addressVM = addressMapper.toVmResponse(address);
//		addressVM.setCountryId(country.getId());
//		addressVM.setStateOrProvinceId(stateOrProvince.getId());
//		addressVM.setDistrictId(district.getId());
//		return addressVM;
//	}
//
//	@Override
//	public void updateAddress(AddressPostVM addressPostVM, Long id) {
//		Address address = addressRepository.getById(id).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id)
//		);
//		Country country = countryRepository.getById(addressPostVM.getCountryId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, addressPostVM.getCountryId())
//		);
//		StateOrProvince stateOrProvince = stateOrProvinceRepository.getById(addressPostVM.getStateOrProvinceId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, addressPostVM.getStateOrProvinceId())
//		);
//		District district = districtRepository.getById(addressPostVM.getDistrictId()).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, addressPostVM.getDistrictId())
//		);
//		addressMapper.partialUpdate(address, addressPostVM);
//
//		address.setCountry(country);
//		address.setStateProvince(stateOrProvince);
//		address.setDistrict(district);
//
//		addressRepository.save(address);
//	}
//
//
//	@Override
//	public void deleteAddress(Long id) {
//		Address address = addressRepository.getById(id).orElseThrow(
//			() -> new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id)
//		);
//		addressRepository.delete(address);
//	}
}
