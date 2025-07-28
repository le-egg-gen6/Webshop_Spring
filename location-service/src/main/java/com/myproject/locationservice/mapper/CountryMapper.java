package com.myproject.locationservice.mapper;

import com.myproject.commonlibs.mapper.BaseMapper;
import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.view_model.country.CountryVM;
import org.mapstruct.Mapper;

/**
 * @author nguyenle
 * @since 5:34 PM Mon 7/28/2025
 */
@Mapper(componentModel = "spring")
public interface CountryMapper extends BaseMapper<Country, CountryVM> {

}
