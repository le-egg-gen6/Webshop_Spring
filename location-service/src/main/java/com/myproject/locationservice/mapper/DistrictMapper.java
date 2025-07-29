package com.myproject.locationservice.mapper;

import com.myproject.commonlibs.mapper.EntityCreateUpdateMapper;
import com.myproject.locationservice.model.District;
import com.myproject.locationservice.view_model.district.DistrictPostVM;
import com.myproject.locationservice.view_model.district.DistrictVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author nguyenle
 * @since 9:57 AM Tue 7/29/2025
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DistrictMapper extends EntityCreateUpdateMapper<District, DistrictPostVM, DistrictVM> {

}
