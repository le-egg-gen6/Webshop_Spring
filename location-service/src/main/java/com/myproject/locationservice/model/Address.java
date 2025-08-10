package com.myproject.locationservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.*;
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

	@Column(length = 30)
	private String zipCode;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

}
