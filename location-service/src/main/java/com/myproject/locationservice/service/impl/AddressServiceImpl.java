package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.constant.PagingAndSortingConstant;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.AddressMapper;
import com.myproject.locationservice.mapper.CountryMapper;
import com.myproject.locationservice.mapper.DistrictMapper;
import com.myproject.locationservice.mapper.StateOrProvinceMapper;
import com.myproject.locationservice.model.Address;
import com.myproject.locationservice.repository.AddressRepository;
import com.myproject.locationservice.repository.CountryRepository;
import com.myproject.locationservice.repository.DistrictRepository;
import com.myproject.locationservice.repository.StateOrProvinceRepository;
import com.myproject.locationservice.service.AddressService;
import com.myproject.locationservice.view_model.address.AddressListVM;
import com.myproject.locationservice.view_model.address.AddressPostVM;
import com.myproject.locationservice.view_model.address.AddressVM;
import com.myproject.locationservice.view_model.district.DistrictVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nguyenle
 * @since 4:17 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final DistrictRepository districtRepository;
    private final AddressRepository addressRepository;

    private final CountryMapper countryMapper;
    private final StateOrProvinceMapper stateOrProvinceMapper;
    private final DistrictMapper districtMapper;
    private final AddressMapper addressMapper;

    @Override
    public AddressListVM getAll(int pageIndex, int pageSize) {
        Sort sort = Sort.by(
                PagingAndSortingConstant.DEFAULT_SORT_DIRECTION,
                PagingAndSortingConstant.DEFAULT_SORT_FIELD
        );
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Address> addressPage = addressRepository.findAll(pageable);
        List<Address> addressList = addressPage.getContent();

        AddressListVM addressListVM = new AddressListVM();
        addressListVM.applyPageableMetadata(addressPage);

        addressListVM.setAddresses(
                addressList.stream().map(addressMapper::toVmResponse).toList()
        );

        return addressListVM;
    }

    @Override
    public AddressVM getById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id)
                );

        DistrictVM districtVM = districtMapper.toVmResponse(address.getDistrict());
        districtVM.setStateProvince(
                stateOrProvinceMapper.toVmResponse(address.getDistrict().getStateProvince())
        );
        districtVM.getStateProvince().setCountry(
                countryMapper.toVmResponse(address.getDistrict().getStateProvince().getCountry())
        );
        AddressVM addressVM = addressMapper.toVmResponse(address);
        addressVM.setDistrict(districtVM);
        return addressVM;
    }

    @Override
    public AddressListVM getPageableAddressesByDistrictId(Long districtID, int pageIndex, int pageSize) {
        Sort sort = Sort.by(
                PagingAndSortingConstant.DEFAULT_SORT_DIRECTION,
                PagingAndSortingConstant.DEFAULT_SORT_FIELD
        );
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Address> addressPage = addressRepository.findByDistrictId(districtID, pageable);
        List<Address> addressList = addressPage.getContent();

        AddressListVM addressListVM = new AddressListVM();
        addressListVM.applyPageableMetadata(addressPage);

        addressListVM.setAddresses(
                addressList.stream().map(addressMapper::toVmResponse).toList()
        );
        return addressListVM;
    }

    @Override
    public Address createAddress(AddressPostVM addressVM) {
        Long districtID = addressVM.getDistrictId();
        boolean isDistrictExist = districtRepository.existsById(districtID);
        if (!isDistrictExist) {
            throw new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, districtID);
        }
        Address address = addressMapper.toModel(addressVM);
        address.setDistrict(districtRepository.getReferenceById(districtID));
        return addressRepository.save(address);
    }

    @Override
    public void updateAddress(AddressPostVM addressVM, Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id)
                );
        Long districtID = address.getDistrict().getId();
        boolean isDistrictExist = districtRepository.existsById(districtID);
        if (!isDistrictExist) {
            throw new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, districtID);
        }
        addressMapper.partialUpdate(address, addressVM);
        address.setDistrict(districtRepository.getReferenceById(districtID));
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        boolean isAddressExist = addressRepository.existsById(id);
        if (!isAddressExist) {
            throw new NotFoundException(ExceptionConstant.ADDRESS_NOT_FOUND, id);
        }
        addressRepository.deleteById(id);
    }
}
