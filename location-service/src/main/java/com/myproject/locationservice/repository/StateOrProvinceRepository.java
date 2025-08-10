package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.StateOrProvince;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 5:25 PM Mon 7/28/2025
 */
@Repository
public interface StateOrProvinceRepository extends JpaRepository<StateOrProvince, Long> {

	boolean existsByNameIgnoreCaseAndCountryId(String name, Long countryId);

	boolean existsByNameIgnoreCaseAndCountryIdAndIdNot(String name, Long countryId, Long id);

	List<StateOrProvince> findByCountryIdOrderByNameAsc(Long countryId);

}
