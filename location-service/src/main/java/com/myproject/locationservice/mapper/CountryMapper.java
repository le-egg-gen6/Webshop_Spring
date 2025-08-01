package com.myproject.locationservice.mapper;

import com.myproject.commonlibs.mapper.EntityCreateUpdateMapper;
import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.view_model.country.CountryPostVM;
import com.myproject.locationservice.view_model.country.CountryVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author nguyenle
 * @since 5:34 PM Mon 7/28/2025
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CountryMapper extends EntityCreateUpdateMapper<Country, CountryPostVM, CountryVM> {

}
