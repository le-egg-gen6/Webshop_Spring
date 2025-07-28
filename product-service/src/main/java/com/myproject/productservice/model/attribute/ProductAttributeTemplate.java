package com.myproject.productservice.model.attribute;

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
 * @since 3:12 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_attribute_template"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeTemplate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_attribute_id", nullable = false)
	private ProductAttribute productAttribute;

	@ManyToOne
	@JoinColumn(name = "product_template_id", nullable = false)
	private ProductTemplate productTemplate;

	private int displayOrder;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductAttributeTemplate)) {
			return false;
		}
		return id != null && id.equals(((ProductAttributeTemplate) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}










