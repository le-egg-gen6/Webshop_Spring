package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 5:24 PM Mon 7/28/2025
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
