package com.myproject.locationservice.controller.storefront;

import com.myproject.locationservice.service.AddressService;
import com.myproject.locationservice.view_model.address.AddressPostVM;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 10:16 AM Tue 7/29/2025
 */
@RestController
@RequestMapping(path = "/storefront/addresses")
@RequiredArgsConstructor
public class StorefrontAddressController {

	private final AddressService addressService;
//
//	@PostMapping
//	public ResponseEntity<?> createAddress(@Valid @RequestBody AddressPostVM addressPostVM) {
//		return ResponseEntity.ok().body(addressService.createAddress(addressPostVM));
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressPostVM addressPostVM) {
//		addressService.updateAddress(addressPostVM, id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getAddress(@PathVariable Long id) {
//		return ResponseEntity.ok(addressService.getById(id));
//	}
//
//	@GetMapping
//	public ResponseEntity<?> getAllAddressList(@RequestParam List<Long> ids) {
//		return ResponseEntity.ok(addressService.findAddressList(ids));
//	}

}
