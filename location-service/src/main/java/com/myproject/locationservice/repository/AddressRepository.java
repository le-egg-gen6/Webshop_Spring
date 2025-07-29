package com.myproject.locationservice.repository;

import com.myproject.locationservice.model.Address;
import java.util.List;
import java.util.Optional;
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

//	List<Address> findAllByIdIn(List<Long> ids);
//
//	@Query(value = "SELECT a FROM Address a JOIN FETCH a.country JOIN FETCH a.stateProvince JOIN FETCH a.district WHERE a.id = :id")
//	Optional<Address> findByIdWithPreFetch(@Param("id") Long id);
//
//	@Query(value = "SELECT a FROM Address a JOIN FETCH a.country JOIN FETCH a.stateProvince JOIN FETCH a.district WHERE a.id IN :ids")
//	List<Address> findByIdInWithPreFetch(@Param("ids") List<Long> ids);

}
