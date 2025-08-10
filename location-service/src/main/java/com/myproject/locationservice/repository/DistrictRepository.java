package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 5:24 PM Mon 7/28/2025
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	List<District> findAllByStateProvinceIdOrderByNameAsc(Long stateProvinceId);

	boolean existsByNameIgnoreCaseAndStateProvinceId(String name, Long stateProvinceId);

	boolean existsByNameIgnoreCaseAndStateProvinceIdAndIdNot(String name, Long stateProvinceId, Long id);
}
