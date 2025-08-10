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

	@Column(length = 500)
	private String location;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "state_province_id", nullable = false)
	private StateOrProvince stateProvince;

	@OneToMany(mappedBy = "district")
	private List<Address> addresses;
}
