package com.myproject.locationservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
	name = "country"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String name;

	@Column(length = 2)
	//ISO 3166-1 Alpha-2 Code
	private String alpha2Code;

	@Column(length = 3)
	//ISO 3166 Alpha-3 Code
	private String alpha3Code;

	private Boolean billingEnabled;

	private Boolean shippingEnabled;

	private Boolean cityEnabled;

	private Boolean zipCodeEnabled;

	private Boolean districtEnabled;

	@OneToMany(mappedBy = "country")
	private List<StateOrProvince> stateProvinces = new ArrayList<>();

}
