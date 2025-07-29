package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 5:24 PM Mon 7/28/2025
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	boolean existsByNameIgnoreCase(String name);

	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

	boolean existsByAlpha2CodeIgnoreCase(String alpha2Code);

	boolean existsByAlpha2CodeIgnoreCaseAndIdNot(String alpha2Code, Long id);
}
