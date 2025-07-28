package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.StateOrProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 5:25 PM Mon 7/28/2025
 */
@Repository
public interface StateOrProvinceRepository extends JpaRepository<StateOrProvince, Long> {

}
