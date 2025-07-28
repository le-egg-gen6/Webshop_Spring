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
 * @since 5:00 PM Mon 7/28/2025
 */
@Entity
@Table(
	name = "district"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String name;

	@Column(length = 500)
	private String type;

	private String location;

	@ManyToOne
	@JoinColumn(name = "state_or_province_id", nullable = false)
	private StateOrProvince stateOrProvince;
}
