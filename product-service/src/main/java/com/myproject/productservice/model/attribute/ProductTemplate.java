package com.myproject.productservice.model.attribute;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.CascadeType;
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
 * @since 3:13 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_template"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTemplate extends AbstractAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "productTemplate", cascade = {CascadeType.PERSIST})
	@Builder.Default
	private List<ProductAttributeTemplate> productAttributeTemplates = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductTemplate)) {
			return false;
		}
		return id != null && id.equals(((ProductTemplate) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
