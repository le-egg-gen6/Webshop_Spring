package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.StateOrProvince;
import java.util.List;
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

	boolean existsByName(String name);

	boolean existsByNameIgnoreCaseAndCountryId(String name, Long countryId);

	boolean existsByNameIgnoreCaseAndCountryIdAndIdNot(String name, Long countryId, Long id);

	List<StateOrProvince> findByCountryIdOrderByNameAsc(Long countryId);

	List<StateOrProvince> findByIdIn(List<Long> ids);

	@Query(value = "SELECT s FROM StateOrProvince s JOIN FETCH s.country WHERE s.id IN :ids")
	List<StateOrProvince> findByIdWithPreFetchCountry(@Param("ids") List<Long> ids);

	@Query(value = "SELECT s FROM StateOrProvince s WHERE s.country.id = :countryId ORDER BY s.updatedAt DESC")
	Page<StateOrProvince> findByCountryId(@Param("countryId") Long countryId, Pageable pageable);

}
