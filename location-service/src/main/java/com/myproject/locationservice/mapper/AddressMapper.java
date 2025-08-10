package com.myproject.locationservice.mapper;

import com.myproject.commonlibs.mapper.EntityCreateUpdateMapper;
import com.myproject.locationservice.model.Address;
import com.myproject.locationservice.view_model.address.AddressPostVM;
import com.myproject.locationservice.view_model.address.AddressVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author nguyenle
 * @since 9:56 AM Tue 7/29/2025
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper extends EntityCreateUpdateMapper<Address, AddressPostVM, AddressVM> {

    @Override
    @Mapping(target = "district", ignore = true)
    AddressVM toVmResponse(Address address);
}
