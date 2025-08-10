package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.Address;
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
 * @since 5:24 PM Mon 7/28/2025
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findByDistrictId(Long districtId, Pageable pageable);

}
