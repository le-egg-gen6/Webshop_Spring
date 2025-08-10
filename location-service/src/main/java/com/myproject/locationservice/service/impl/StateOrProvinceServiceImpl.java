package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.constant.PagingAndSortingConstant;
import com.myproject.commonlibs.exception.DuplicatedException;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.CountryMapper;
import com.myproject.locationservice.mapper.StateOrProvinceMapper;
import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.repository.CountryRepository;
import com.myproject.locationservice.repository.StateOrProvinceRepository;
import com.myproject.locationservice.service.StateOrProvinceService;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceListVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvincePostVM;
import com.myproject.locationservice.view_model.state_or_province.StateOrProvinceVM;
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
 * @since 2:10 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StateOrProvinceServiceImpl implements StateOrProvinceService {

    private final StateOrProvinceRepository stateOrProvinceRepository;
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;
    private final StateOrProvinceMapper stateOrProvinceMapper;


    @Override
    public StateOrProvinceListVM getAll(int pageIndex, int pageSize) {
        Sort sort = Sort.by(
                PagingAndSortingConstant.DEFAULT_SORT_DIRECTION,
                PagingAndSortingConstant.DEFAULT_SORT_FIELD
        );
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<StateOrProvince> page = stateOrProvinceRepository.findAll(pageable);
        List<StateOrProvince> stateOrProvinces = page.getContent();

        StateOrProvinceListVM vm = new StateOrProvinceListVM();
        vm.applyPageableMetadata(page);

        vm.setStateOrProvinces(
                stateOrProvinces.stream().map(stateOrProvinceMapper::toVmResponse).toList()
        );
        return vm;
    }

    @Override
    public StateOrProvinceVM getById(Long id) {
        StateOrProvince stateOrProvince = stateOrProvinceRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, id)
                );
        StateOrProvinceVM vm = stateOrProvinceMapper.toVmResponse(stateOrProvince);
        vm.setCountry(countryMapper.toVmResponse(stateOrProvince.getCountry()));
        return vm;
    }

    @Override
    public List<StateOrProvinceVM> getAllByCountryId(Long countryId) {
        return stateOrProvinceRepository.findByCountryIdOrderByNameAsc(countryId)
                .stream()
                .map(stateOrProvinceMapper::toVmResponse)
                .toList();
    }


    @Override
    public StateOrProvince createStateOrProvince(StateOrProvincePostVM stateOrProvinceVM) {
        Long countryId = stateOrProvinceVM.getCountryId();
        boolean isCountryExist = countryRepository.existsById(countryId);
        if (!isCountryExist) {
            throw new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, countryId);
        }
        boolean isStateOrProvinceExist = stateOrProvinceRepository.existsByNameIgnoreCaseAndCountryId(stateOrProvinceVM.getName(), countryId);
        if (isStateOrProvinceExist) {
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
        boolean existedStateOrProvinceWithSameName = stateOrProvinceRepository.existsByNameIgnoreCaseAndCountryIdAndIdNot(stateOrProvinceVM.getName(), stateOrProvince.getCountry().getId(), id);
        if (existedStateOrProvinceWithSameName) {
            throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, stateOrProvinceVM.getName());
        }
        Long countryId = stateOrProvinceVM.getCountryId();
        boolean isCountryExist = countryRepository.existsById(countryId);
        if (!isCountryExist) {
            throw new NotFoundException(ExceptionConstant.COUNTRY_NOT_FOUND, countryId);
        }
        stateOrProvinceMapper.partialUpdate(stateOrProvince, stateOrProvinceVM);
        stateOrProvince.setCountry(countryRepository.getReferenceById(countryId));

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
