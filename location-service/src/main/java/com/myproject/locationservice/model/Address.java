package com.myproject.locationservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 4:59 PM Mon 7/28/2025
 */
@Entity
@Table(
	name = "address"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500)
	private String contactName;

	@Column(length = 30)
	private String phone;

	@Column(length = 500, name = "address_line_1")
	private String addressLine1;

	@Column(length = 500, name = "address_line_2")
	private String addressLine2;

	@Column(length = 500)
	private String city;

	@Column(length = 30)
	private String zipCode;

	@ManyToOne
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

	@ManyToOne
	@JoinColumn(name = "state_or_province_id", nullable = false)
	private StateOrProvince stateOrProvince;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

}
