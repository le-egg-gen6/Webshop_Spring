package com.myproject.locationservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	name = "state_or_province"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateOrProvince extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255)
	private String code;

	@Column(nullable = false, length = 500)
	private String name;

	@Column(length = 255)
	private String type;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

}
