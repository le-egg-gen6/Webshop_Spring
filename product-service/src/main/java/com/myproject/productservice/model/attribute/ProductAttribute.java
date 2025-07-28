package com.myproject.productservice.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * @since 3:11 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_attribute"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "product_attribute_group_id")
	private ProductAttributeGroup productAttributeGroup;

	@OneToMany(mappedBy = "productAttribute")
	@JsonIgnore
	@Builder.Default
	private List<ProductAttributeTemplate> productAttributeTemplates = new ArrayList<>();

	@OneToMany(mappedBy = "productAttribute")
	@Builder.Default
	private List<ProductAttributeValue> attributeValues = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductAttribute)) {
			return false;
		}
		return id != null && id.equals(((ProductAttribute) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}