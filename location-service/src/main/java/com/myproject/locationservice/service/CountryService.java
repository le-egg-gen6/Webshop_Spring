package com.myproject.locationservice.service;

import com.myproject.locationservice.model.Country;
import com.myproject.locationservice.view_model.country.CountryListVM;
import com.myproject.locationservice.view_model.country.CountryPostVM;
import com.myproject.locationservice.view_model.country.CountryVM;
import java.util.List;

/**
 * @author nguyenle
 * @since 10:26 AM Tue 7/29/2025
 */
public interface CountryService {

	CountryVM getById(Long id);

	CountryListVM getPageableCountries(int pageIndex, int pageSize);

	Country createCountry(CountryPostVM countryPostVM);

	void updateCountry(CountryPostVM countryPostVm, Long id);

	void deleteCountry(Long id);
}
