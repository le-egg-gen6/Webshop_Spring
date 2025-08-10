package com.myproject.locationservice.service;

import com.myproject.locationservice.model.Address;
import com.myproject.locationservice.view_model.address.AddressListVM;
import com.myproject.locationservice.view_model.address.AddressPostVM;
import com.myproject.locationservice.view_model.address.AddressVM;

/**
 * @author nguyenle
 * @since 4:17 PM Tue 7/29/2025
 */
public interface AddressService {

    AddressListVM getAll(int pageIndex, int pageSize);

    AddressVM getById(Long id);

    AddressListVM getPageableAddressesByDistrictId(Long districtID, int pageIndex, int pageSize);

    Address createAddress(AddressPostVM addressVM);

    void updateAddress(AddressPostVM addressVM, Long id);

    void deleteAddress(Long id);

}
