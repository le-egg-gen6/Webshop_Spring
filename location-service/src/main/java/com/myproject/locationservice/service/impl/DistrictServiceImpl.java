package com.myproject.locationservice.service.impl;

import com.myproject.commonlibs.constant.PagingAndSortingConstant;
import com.myproject.commonlibs.exception.DuplicatedException;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.locationservice.exception.ExceptionConstant;
import com.myproject.locationservice.mapper.CountryMapper;
import com.myproject.locationservice.mapper.DistrictMapper;
import com.myproject.locationservice.mapper.StateOrProvinceMapper;
import com.myproject.locationservice.model.District;
import com.myproject.locationservice.model.StateOrProvince;
import com.myproject.locationservice.repository.DistrictRepository;
import com.myproject.locationservice.repository.StateOrProvinceRepository;
import com.myproject.locationservice.service.DistrictService;
import com.myproject.locationservice.view_model.district.DistrictListVm;
import com.myproject.locationservice.view_model.district.DistrictPostVM;
import com.myproject.locationservice.view_model.district.DistrictVM;
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
 * @since 4:04 PM Tue 7/29/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final StateOrProvinceRepository stateOrProvinceRepository;
    private final DistrictRepository districtRepository;

    private final CountryMapper countryMapper;
    private final StateOrProvinceMapper stateOrProvinceMapper;
    private final DistrictMapper districtMapper;

    @Override
    public DistrictListVm getAll(int pageIndex, int pageSize) {
        Sort sort = Sort.by(
                PagingAndSortingConstant.DEFAULT_SORT_DIRECTION,
                PagingAndSortingConstant.DEFAULT_SORT_FIELD
        );
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<District> districtPage = districtRepository.findAll(pageable);
        List<District> districtList = districtPage.getContent();

        DistrictListVm districtListVm = new DistrictListVm();
        districtListVm.applyPageableMetadata(districtPage);

        districtListVm.setDistricts(
                districtList.stream().map(districtMapper::toVmResponse).toList()
        );
        return districtListVm;
    }

    @Override
    public DistrictVM getById(Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, id)
                );
        StateOrProvince stateOrProvince = district.getStateProvince();
        StateOrProvinceVM stateOrProvinceVM = stateOrProvinceMapper.toVmResponse(stateOrProvince);
        stateOrProvinceVM.setCountry(countryMapper.toVmResponse(stateOrProvince.getCountry()));

        DistrictVM districtVM = districtMapper.toVmResponse(district);
        districtVM.setStateProvince(stateOrProvinceVM);

        return districtVM;
    }

    @Override
    public List<DistrictVM> getAllByStateProvinceId(Long stateProvinceId) {
        List<District> districtList = districtRepository.findAllByStateProvinceIdOrderByNameAsc(stateProvinceId);
        return districtList.stream().map(districtMapper::toVmResponse).toList();
    }

    @Override
    public District createDistrict(DistrictPostVM districtVM) {
        Long stateOrProvinceId = districtVM.getStateProvinceId();
        boolean isStateOrProvinceExist = stateOrProvinceRepository.existsById(stateOrProvinceId);
        if (!isStateOrProvinceExist) {
            throw new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, stateOrProvinceId);
        }
        boolean isDistrictExist = districtRepository.existsByNameIgnoreCaseAndStateProvinceId(districtVM.getName(), stateOrProvinceId);
        if (isDistrictExist) {
            throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, districtVM.getName());
        }
        District district = districtMapper.toModel(districtVM);
        district.setStateProvince(stateOrProvinceRepository.getReferenceById(stateOrProvinceId));
        return districtRepository.save(district);
    }

    @Override
    public void updateDistrict(DistrictPostVM districtVM, Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, id)
                );
        boolean existedDistrictWithSameName = districtRepository.existsByNameIgnoreCaseAndStateProvinceIdAndIdNot(
                district.getName(), district.getStateProvince().getId(), id
        );
        if (existedDistrictWithSameName) {
            throw new DuplicatedException(ExceptionConstant.NAME_ALREADY_EXISTED, districtVM.getName());
        }
        Long stateOrProvinceId = districtVM.getStateProvinceId();
        boolean isStateOrProvinceExist = stateOrProvinceRepository.existsById(stateOrProvinceId);
        if (!isStateOrProvinceExist) {
            throw new NotFoundException(ExceptionConstant.STATE_OR_PROVINCE_NOT_FOUND, stateOrProvinceId);
        }
        districtMapper.partialUpdate(district, districtVM);
        district.setStateProvince(stateOrProvinceRepository.getReferenceById(stateOrProvinceId));

        districtRepository.save(district);
    }

    @Override
    public void deleteDistrict(Long id) {
        boolean isDistrictExist = districtRepository.existsById(id);
        if (!isDistrictExist) {
            throw new NotFoundException(ExceptionConstant.DISTRICT_NOT_FOUND, id);
        }
        districtRepository.deleteById(id);
    }
}
