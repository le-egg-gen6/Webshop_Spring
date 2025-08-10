package com.myproject.locationservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

	@OneToMany(mappedBy = "stateProvince")
	private List<District> districts;

}
